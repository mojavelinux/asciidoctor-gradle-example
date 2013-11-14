package org.asciidoctor.example

import org.asciidoctor.Asciidoctor
import org.asciidoctor.extension.ExtensionRegistry

class ExampleExtensionRegistry implements ExtensionRegistry {

	void register(Asciidoctor asciidoctor) {
		asciidoctor.javaExtensionRegistry().block 'yell', YellBlock
	}

}
