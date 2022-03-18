package com.waracle.cakemgr.data;

import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.entity.UserCakeMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface UserCakeRepository extends JpaRepository<UserCakeMapping, Integer> {

}
