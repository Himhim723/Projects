package com.openrice.demoopenrice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openrice.demoopenrice.entity.Member;

public interface MemberRepository extends JpaRepository<Member,String>{
  
}
