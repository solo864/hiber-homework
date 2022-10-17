package az.online.shop.service;

import az.online.shop.dao.PersonalInfoRepository;
import az.online.shop.dto.PersonalInfoReadDto;
import az.online.shop.entity.PersonalInfo;
import az.online.shop.exception.NotFoundException;
import az.online.shop.mapper.PersonalInfoMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonalInfoService {

    private final PersonalInfoRepository personalInfoRepository;
    private final PersonalInfoMapper personalInfoMapper;

    public Optional<PersonalInfoReadDto> getById(Integer id) {
        var personalInfo = personalInfoRepository.findById(id);
        return personalInfo.map(personalInfoMapper::mapFrom);
    }
}
