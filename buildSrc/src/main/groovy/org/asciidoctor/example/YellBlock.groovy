package org.asciidoctor.example

import org.asciidoctor.extension.BlockProcessor
import org.asciidoctor.ast.AbstractBlock
import org.asciidoctor.extension.Reader

//@Context(':paragraph')
//or @Contexts([':paragraph'])
//@ContentModel(SIMPLE)
class YellBlock extends BlockProcessor {
    YellBlock(String name, Map<String, Object> config) {
        super(name, config);
        config.contexts = [':paragraph']
        config.content_mode = ':simple'
    }

    def process(AbstractBlock parent, Reader reader, Map<String, Object> attributes) {
        createBlock(parent, 'paragraph', reader.lines()*.toUpperCase().join(''), attributes, [:])
    }
}
