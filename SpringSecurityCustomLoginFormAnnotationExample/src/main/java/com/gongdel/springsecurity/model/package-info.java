package com.gongdel.springsecurity.model;
/**
    User는 다수의 권한[DBA, ADMIN, USER]을 가지며, user에게 하나 이상 할당될 수 있다.
    그러므로 User와 UserProfile[role] 사이는 'MANY-TO-MAY' 관계이다.
    우리는 오직 주어진 사용자[반대의 경우에도 마찬가지]의 권한을 찾는 데에만 관심이 있기 때문에,
    이 관계는 단방향 관계[User에서 UserProfile]를 유지해야 한다.
    우리는 Join table을 사용하여 Many-To-Many assocation을 사용한다.
 **/