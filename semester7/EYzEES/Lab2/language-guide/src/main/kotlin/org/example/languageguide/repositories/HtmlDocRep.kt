package org.example.languageguide.repositories

import org.example.languageguide.entities.HtmlDoc
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HtmlDocRep: JpaRepository<HtmlDoc, Long> {

}