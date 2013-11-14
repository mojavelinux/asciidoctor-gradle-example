package org.asciidoctor.example

import org.asciidoctor.extension.Treeprocessor
import org.asciidoctor.internal.AbstractBlock
import org.asciidoctor.internal.DocumentRuby
import org.asciidoctor.internal.Reader

class ConsoleTreeprocessor extends Treeprocessor {
    ConsoleTreeprocessor(DocumentRuby documentRuby) {
        super(documentRuby)
    }

    void process() {
        document.blocks().eachWithIndex { item, index ->
            lines = item.lines()
            if (lines.size() > 0 && lines.first.startsWith('$')) {
                blocks[index] = convertToListing(item);
            }
        }
    }

    AbstractBlock convertToListing(AbstractBlock block) {
        def attributes = block.attributes()
        attributes.role = 'terminal'
        def resultLines = new StringBuilder()
        def lines = block.lines()

        lines.each { line ->
            if (line.startsWith('$')) {
                resultLines.append('<span class="command">')
                        .append(line.substring(2, line.length()))
                        .append('</span>')
            }
            else {
                resultLines.append(line);
            }
        }

        createBlock(document, 'listing', resultLines.toString(), attributes, [:])
    }
}
