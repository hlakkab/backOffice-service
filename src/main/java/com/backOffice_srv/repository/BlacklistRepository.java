package com.backOffice_srv.repository;

import com.backOffice_srv.modele.Blacklist;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist,Integer> {

    @Query(value = "SELECT * FROM blacklist where archived = 0",
            nativeQuery = true)
    List<Blacklist> getBlacklist(Pageable pageable);

    @Query(value = "SELECT * FROM blacklist where first_name=?1 and last_name=?2 and phone_number=?3 and archived = 0",
            nativeQuery = true)
    Blacklist checkClientINBlacklist(String firstName, String lastName, String phoneNumber);

    @Query(value = "SELECT * FROM blacklist where id_client=?1",
            nativeQuery = true)
    Blacklist getIdclient(Integer id);
}