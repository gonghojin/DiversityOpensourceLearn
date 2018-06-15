package com.gongdel.springsecurity.globalsecuritymethod;
/**
    @EnableGlobalMethodSecurity는 몇 개의 argument를 제공한다
        1. prePostEnabled : Security의 pre post annotation[@PreAuthorize, @PostAuthorize]이 활성화될 지를 결정한다.
        2. secureEnabled : Security의의 secured annotation[@Secoured] 활성 여부 결정
        3. jsr250Enabled : JSR-250 annotations[@RolesAllowed] 활성 여부 결정

        같은 application에서 하나 이상의 annotation을 사용할 수 있지만, interface나 class를 위해서 오직 하나의 타입만 사용되어야 한다.(그렇지 않으면 잘 정의되지 않을 수 있다.)
        만약 특정한 method에 적용된 두 개의 annotation이 찾아진다면. 그들 중 오직 하나만 적용될 것이다.

        우리는 위에서 언급한 처음 두 가지를 살펴본다.

 **/