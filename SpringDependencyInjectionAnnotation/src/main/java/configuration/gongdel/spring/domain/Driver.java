package configuration.gongdel.spring.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("driver")
public class Driver {
    // Field wired
    //@Autowired
    private License license;

    // Method wired
    /*@Autowired
    public void setLicense(License license) {
        this.license = license;
    }*/

    // Constructor wired
    @Autowired
    public Driver(License license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "license=" + license +
                '}';
    }
}

/**
 * 기본적으로, @Autowired annotation은 field가 autowired가 되어진다고 확실시 한다. autowiring이 실패했을 경우에, Spring은 exception을 발생시킨다.
 * 그러나 autowiring을 선택적으로 만드는 몇 가지 방법이 있다. @Autowired의 required attribute를 'false'로 설정하면, 해당 field의 autowiring을 선택적으로 만든다.
 * 따라서 만약 dependency를 찾을 수 없다면 건너뛴다.(null을 남김).
 * ex) @Autowired(required=false)
 *
 * 반면 표준 @Resource annotation은 이같은 유연성이 없다. 따라서 @Resource를 찾지 못하면 exception을 발생시킨다.
 *
 * @Resource와 @Autowired는 거의 차이성이 없지만,  @Resource는 선택성이 없다는 것과 @Autowired 안에는 bean name을 통한 autowring이 없다 것뿐
 * 대체로, @Resource나 XML 안에 autowire attribute에 비해 @Autowired가 광범위하게 사용된다
 */
