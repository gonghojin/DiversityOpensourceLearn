package com.gongdel.spring.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Bond {

    /*
    @Autowired
    private Car car;

      No qualifying bean of type [com.gongdel.spring.domain.Car] is defined: expected single matching bean but found 2: Ferari,Mustang
      이유 : @Autowired는 type으로 inject되는데, Car type이 두 가지이기 때문에 에러 발생
      따라서 이럴 때 좀더 특정지어줄 방법이 필요 - @Qulifier
    */

    @Autowired
    @Qualifier("Mustang")
    private Car car;

    public void showCar() {
        car.getCarName();
    }
}
