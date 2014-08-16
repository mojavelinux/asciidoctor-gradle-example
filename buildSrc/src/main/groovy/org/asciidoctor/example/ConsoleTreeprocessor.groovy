package org.asciidoctor.example

import org.asciidoctor.extension.Treeprocessor
import org.asciidoctor.ast.Document
import org.asciidoctor.ast.AbstractBlock
import org.asciidoctor.extension.Reader

class ConsoleTreeprocessor extends Treeprocessor {
    ConsoleTreeprocessor(Map<String, Object> config) {
        super(config);
    }

    Document process(Document document) {
        document.blocks().eachWithIndex { item, index ->
            lines = item.lines()
            if (lines.size() > 0 && lines.first.startsWith('$')) {
                blocks[index] = convertToListing(item);
            }
        }
        null
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

        createBlock(block, 'listing', resultLines.toString(), attributes, [:])
    }
}
