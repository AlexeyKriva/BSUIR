package org.project.searchsystem.models.nlpModels;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AbbreviationRelation {
    public static final Map<String, String> ABBREVIATION_AND_EXPLANATION_OF_RELATIONS = new HashMap<String, String>() {{
        put("acl", "Модификатор глагола, который является зависимым от существительного, но не является его аргументом.");
        put("advcl", "Клауза, которая является зависимой от глагола и обычно выражает дополнительную информацию о действии.");
        put("advmod", "Наречие, которое модифицирует другое наречие, глагол или прилагательное.");
        put("amod", "Определение прилагательного, которое является зависимым от существительного и модифицирует его.");
        put("appos", "Элемент, который является эквивалентным или близким другому элементу.");
        put("aux", "Вспомогательный глагол.");
        put("case", "Предлог, который устанавливает связь между главным словом и зависимым словом.");
        put("cc", "Связывающий союз, который соединяет два однородных элемента.");
        put("conj", "Конъюнкция, которая соединяет два однородных элемента.");
        put("cop", "Глагол-связка (обычно 'быть') между подлежащим и предикатом.");
        put("csubj", "Подлежащее в зависимости от контролирующего глагола.");
        put("det", "Определитель.");
        put("nsubj", "Подлежащее");
        put("obj", "Объект");
        put("conj:and", "Соединение с помощью 'и'");
        put("nmod:by", "Модификатор объекта");
        put("mark", "Маркер, который обычно связывает вспомогательный глагол с его зависимой клозой.");
        put("nmod", "Модификатор существительного.");
        put("nmod:poss", "Модификатор существительного, который указывает на принадлежность или владение. Это отношение обычно выражается с помощью притяжательных местоимений или их эквивалентов.");
        put("nummod", "Модификатор числительного.");
        put("parataxis", "Клауза, которая стоит рядом с другой клаузой, но не является ее зависимой.");
        put("punct", "Знак пунктуации.");
        put("root", "Корень дерева зависимостей.");
        put("xcomp", "Одиночный глагол, который является зависимым от другого глагола, но не является его подлежащим или дополнением.");
        put("nmod:poss", "Одиночный глагол, который является зависимым от другого глагола, но не является его подлежащим или дополнением.");
        put("advcl:if", "Придаточное предложение, связанное с условием.");
        put("nsubj:xsubj", "Определенный тип подчиненного подлежащего, например, подлежащее в подчиненном предложении, связанном с глаголом в основном предложении.");
        put("obl:from", "Обстоятельство, связанное с местом.");
        put("compound:prt", "Слово, состоящее из двух или более отдельных слов.");
        put("fixed", "Слово или словосочетание, которое всегда встречается вместе с другим словом.");
    }};
}