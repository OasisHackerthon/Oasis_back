package Mirthon.Oasis_back.service;

import Mirthon.Oasis_back.domain.Fountain;
import Mirthon.Oasis_back.domain.Notice;
import Mirthon.Oasis_back.repository.FountainRepository;
import Mirthon.Oasis_back.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static Mirthon.Oasis_back.domain.NoticeType.단수;
import static Mirthon.Oasis_back.domain.NoticeType.수질부적합;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final FountainRepository fountainRepository;


    public List<Notice> getAllNotices(Long fountainId) {
        Fountain fountain = fountainRepository.getById(fountainId);
        return noticeRepository.findByFountain(fountain);
    }

    public Notice getNoticeById(Long noticeId) {
        return noticeRepository.findById(noticeId).get();
    }

    public List<Notice> createNotice(Long fountainId) {
        Notice notice = new Notice();
        Fountain fountain = fountainRepository.getById(fountainId);
        notice.setFountain(fountain);
        notice.setNoticeType(수질부적합);


        String dateString = LocalDateTime.now().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 문자열을 LocalDateTime으로 파싱
        LocalDateTime date = LocalDateTime.parse(dateString, formatter);
        notice.setNoticeDate(date);
        notice.setNoticeContent("현재" + fountain.getFountainName()+"의 수질이 부적합니다. 주의하세요.");
        noticeRepository.save(notice);
        return noticeRepository.findByFountain(fountain);

    }

    public List<Notice> createWaterNotice(Long fountainId) {
        Notice notice = new Notice();
        Fountain fountain = fountainRepository.getById(fountainId);
        notice.setFountain(fountain);
        notice.setNoticeType(단수);
        notice.setNoticeDate(LocalDateTime.now());
        notice.setNoticeContent("현재" + fountain.getFountainName()+"의 물이 없습니다. 다른곳을 알아봐주세요.");
        noticeRepository.save(notice);
        return noticeRepository.findByFountain(fountain);
    }

    public Notice updateNotice(Long noticeId, Notice updatedNotice) {
        if (noticeRepository.existsById(noticeId)) {
            updatedNotice.setNoticeId(noticeId);
            return noticeRepository.save(updatedNotice);
        } else {
            System.out.println("Notice not found with id: " + noticeId);
            throw new IllegalStateException();
        }
    }

    public List<Notice> deleteNotice(Long fountainId) {
        Fountain fountain = fountainRepository.getById(fountainId);
        List<Notice> notices = noticeRepository.findByFountain(fountain);

        for (Notice notice : notices) {
            // 여기에서 삭제 조건을 설정하고, 해당 조건을 만족하면 삭제 수행
            if (notice.getNoticeType()==수질부적합) {
                noticeRepository.deleteById(notice.getNoticeId());
            }
        }
        return noticeRepository.findByFountain(fountain);
    }

    public List<Notice> deleteWaterNotice(Long fountainId) {
        Fountain fountain = fountainRepository.getById(fountainId);
        List<Notice> notices = noticeRepository.findByFountain(fountain);

        for (Notice notice : notices) {
            // 여기에서 삭제 조건을 설정하고, 해당 조건을 만족하면 삭제 수행
            if (notice.getNoticeType()==단수) {
                noticeRepository.deleteById(notice.getNoticeId());
            }
        }
        return noticeRepository.findByFountain(fountain);
    }
}
