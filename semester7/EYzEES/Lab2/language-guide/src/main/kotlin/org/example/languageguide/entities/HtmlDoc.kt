package org.example.languageguide.entities

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime


@Entity
@Table(name = "html_doc")
class HtmlDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var name: String = "default"

    var content: String = "default"

    var addedAt: LocalDateTime = LocalDateTime.now()

    var weight: Long = 0

    constructor(name: String, content: String, addedAt: LocalDateTime, weight: Long) {
        this.name = name
        this.content = content
        this.addedAt = addedAt
        this.weight = weight
    }

    constructor()

    override fun toString(): String {
        return "HtmlDoc(id=$id, name='$name', content='$content', addedAt=$addedAt, weight=$weight)"
    }
}