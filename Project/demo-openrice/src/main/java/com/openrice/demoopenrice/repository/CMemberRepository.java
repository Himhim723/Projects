package com.openrice.demoopenrice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openrice.demoopenrice.entity.BMember;
import com.openrice.demoopenrice.entity.CMember;
import com.openrice.demoopenrice.entity.Member;

public interface CMemberRepository extends JpaRepository<CMember,Long>{
  
  CMember findByMember(Member member);
}
