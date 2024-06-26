# RuleBaseRewriter

RuleBaseRewriter is a recursive text rewriter.

Usage : RuleBaseRewriter config.tsv text

numbers-fr-FR.tsv is providing as an example of recursive rewriting to convert numbers in their literal forms (ex: "123" to "cent-vingt-trois").

1) Each line of the configuration TSV is a rule of the form:

PATTERN	REWRITE

PATTERN is a text to find using a Java regexp. Enclosed between 2 pipe | chars for a better reading of possible spaces.

REWRITE is a text to write using possible sub-group matching of the PATTERN Java regexp. Enclosed between 2 pipe | chars for a better reading of possible spaces.

2) all the rules of the TSV are applied one by one in sequence. The line order is thus important.

3) all the rules of TSV file are applied again and again till the obtained text is no more changing.

