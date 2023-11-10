package Mirthon.Oasis_back.service;

import Mirthon.Oasis_back.domain.Fountain;
import Mirthon.Oasis_back.repository.FountainRepository;
import Mirthon.Oasis_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import static Mirthon.Oasis_back.domain.FountainState.오염;
import static Mirthon.Oasis_back.domain.FountainState.정상;
import static Mirthon.Oasis_back.domain.FountainWaterState.단수;
import static Mirthon.Oasis_back.domain.FountainWaterState.음수가능;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class FountainService {
    private final FountainRepository fountainRepository;
    private final UserRepository userRepository;

    public Fountain createFountain(Fountain fountain) {
        return fountainRepository.save(fountain);
    }

    public List<Fountain> getAllFountains(Long userId) {
        return (List<Fountain>) fountainRepository.findByUserId(userId);
    }

    public Fountain getFountainByIds(Long userId, Long fountainId) {
        return fountainRepository.findByUserIdAndFountainId(userId,fountainId);
    }

    public Fountain updateFountain(Long id, Fountain updatedFountain) {
        if (fountainRepository.existsById(id)) {
            updatedFountain.setFountainId(id);

        }
        return fountainRepository.save(updatedFountain);
    }

    public void deleteFountain(Long id) {
        fountainRepository.deleteById(id);
    }

    public Fountain changeFountainState(Long userId, Long id) {
        Fountain fountain = fountainRepository.findByUserIdAndFountainId(userId,id);
        if(fountain.getFountainState() == 오염) {
            fountain.setFountainState(정상);
        }else {
            fountain.setFountainState(오염);
        }
        return fountain;
    }

    public Fountain changeFountainWaterState(Long userId, Long id) {
        Fountain fountain = fountainRepository.findByUserIdAndFountainId(userId,id);
        if(fountain.getFountainWaterState() == 단수) {
            fountain.setFountainWaterState(음수가능);
        }else {
            fountain.setFountainWaterState(단수);
        }
        return fountain;
    }

    public void readJsonAndSaveFountains(String filePath, Long userId) throws Exception {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(filePath);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        JSONArray dataArray = (JSONArray) jsonObject.get("DATA");

        for (Object data : dataArray) {
            JSONObject newsObject = (JSONObject) data;

            String title = (String) newsObject.get("cot_conts_name");
            Double locationX =  Double.parseDouble((String)newsObject.get("lng"));
            Double locationY =  Double.parseDouble((String)newsObject.get("lat"));
            String address = (String) newsObject.get("cot_addr_full_old");

            // Fountain 객체 생성 및 저장
            Fountain fountain = new Fountain();
            fountain.setFountainName(title);
            fountain.setLocation_x(locationX);
            fountain.setLocation_y(locationY);
            fountain.setFountainAddress(address);
            fountain.setVisited(false);
            fountain.setFoundtainDetail(address+"에 존재하는 공원입니다. 산책을 즐겨보세요!");
            fountain.setFountainState(정상);
            fountain.setFountainWaterState(음수가능);
            fountain.setUserId(userId);


            // Fountain 저장
            fountainRepository.save(fountain);
        }
    }

}
