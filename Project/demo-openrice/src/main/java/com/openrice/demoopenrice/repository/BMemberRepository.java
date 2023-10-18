package com.openrice.demoopenrice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openrice.demoopenrice.entity.BMember;
import com.openrice.demoopenrice.entity.Member;

public interface BMemberRepository extends JpaRepository<BMember,Long>{
  
  BMember findByMember(Member member);
  
}
