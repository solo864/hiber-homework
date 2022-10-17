package az.online.shop.mapper;

public interface Mapper <F,T>{
    T mapFrom(F object);
}