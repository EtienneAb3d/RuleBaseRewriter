# RuleBaseRewriter

**RuleBaseRewriter** is a recursive text rewriter.

Usage : RuleBaseRewriter config.tsv text

1) Each line of the configuration TSV is a rule of the form:

```PATTERN[TAB]REWRITE```

*PATTERN* is a text to find using a Java regexp. Enclosed between 2 pipe chars "|" for a better reading of possible spaces.

*REWRITE* is a text to write using possible sub-group matching of the *PATTERN* Java regexp. Enclosed between 2 pipe chars "|" for a better reading of possible spaces.

2) All the rules of the TSV are applied one by one in sequence. The line order is thus important.

3) All the rules of the TSV file are applied again and again till the obtained text is no more changing.

# Use case: convert numbers in their literals

```numbers-fr-FR.tsv``` is providing as an example of recursive rewriting to convert numbers in their literal forms (ex: "123" to "cent-vingt-trois").

**Input:** [0, 1, 5, 10, 11, 15, 20, 21, 30, 35, 50, 51, 68, 70, 75, 99, 100, 101, 105, 111, 123, 168, 171, 175, 199, 200, 201, 555, 999, 1000, 1001, 1111, 1199, 1234, 1999, 2000, 2001, 2020, 2021, 2345, 9999, 10000, 11111, 12345, 123456, 654321, 999999]

**Output:** [zero, un, cinq, dix, onze, quinze, vingt, vingt-et-un, trente, trente-cinq, cinquante, cinquante-et-un, soixante-huit, soixante-dix, soixante-quinze, quatre-vingt-dix-neuf, cent, cent-un, cent-cinq, cent-onze, cent-vingt-trois, cent-soixante-huit, cent-soixante-et-onze, cent-soixante-quinze, cent-quatre-vingt-dix-neuf, deux-cents, deux-cent-un, cinq-cent-cinquante-cinq, neuf-cent-quatre-vingt-dix-neuf, mille, mille-un, mille-cent-onze, mille-cent-quatre-vingt-dix-neuf, mille-deux-cent-trente-quatre, mille-neuf-cent-quatre-vingt-dix-neuf, deux-milles, deux-mille-un, deux-mille-vingt, deux-mille-vingt-et-un, deux-mille-trois-cent-quarante-cinq, neuf-mille-neuf-cent-quatre-vingt-dix-neuf, dix-milles, onze-mille-cent-onze, douze-mille-trois-cent-quarante-cinq, cent-vingt-trois-mille-quatre-cent-cinquante-six, six-cent-cinquante-quatre-mille-trois-cent-vingt-et-un, neuf-cent-quatre-vingt-dix-neuf-mille-neuf-cent-quatre-vingt-dix-neuf]
