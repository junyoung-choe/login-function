package loginTest.loginfunction.repository;

import loginTest.loginfunction.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// DAO -> 에 Entitity를 적용하고 여기 저장소에서 기능을 구현한다 !
// 이렇게 구현하면 메소드가 다 정의 되어있는 것이다 !
// JPA 기능으로 여기 해당 인터페이스가 클래스와 같은 역할을 한다 !
@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    /*
save(S entity): 엔티티를 저장하거나 업데이트합니다.
findOne(ID id): 주어진 ID로 엔티티를 조회합니다.
findAll(): 모든 엔티티를 조회합니다.
count(): 엔티티의 총 수를 반환합니다.
delete(ID id): 주어진 ID로 엔티티를 삭제합니다.
deleteAll(): 모든 엔티티를 삭제합니다.

수정 기능은 save 를 이용해서 수정하거나
해당 아이디가 없으면 추가하면된다 !
     */
}
