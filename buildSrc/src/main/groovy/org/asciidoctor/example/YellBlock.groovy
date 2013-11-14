package org.asciidoctor.example

import org.asciidoctor.extension.BlockProcessor
import org.asciidoctor.internal.AbstractBlock
import org.asciidoctor.internal.DocumentRuby
import org.asciidoctor.internal.Reader

class YellBlock extends BlockProcessor {

    static {
        config.contexts = [':paragraph']
        config.content_model = ':simple'
    }
 
    YellBlock(String context, DocumentRuby documentRuby) {
        super(context, documentRuby)
    }

    def process(AbstractBlock parent, Reader reader, Map<String, Object> attributes) {
        // can't pass attributes
        createBlock(document, 'paragraph', reader.lines()*.toUpperCase().join(''), [:], [:])
    }

}
