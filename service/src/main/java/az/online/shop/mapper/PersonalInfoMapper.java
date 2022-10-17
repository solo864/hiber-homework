package az.online.shop.mapper;

import az.online.shop.dto.PersonalInfoReadDto;
import az.online.shop.entity.PersonalInfo;

public class PersonalInfoMapper implements Mapper<PersonalInfo, PersonalInfoReadDto>{

    @Override
    public PersonalInfoReadDto mapFrom(PersonalInfo info) {
        return PersonalInfoReadDto.builder()
                .gender(info.getGender())
                .address(info.getAddress())
                .phoneNumber(info.getPhoneNumber())
                .build();
    }
}