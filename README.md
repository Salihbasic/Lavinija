# Lavinija

Whitaker's WORDS, now in Kotlin.

# About

Since the original WORDS program was written back in the '80s and '90s, it has
received practically no updates or modernisations. The original code was poorly documented and
massive, thus proving hard to modernise or adapt into newer frameworks and technologies. 

This project, maintained solely by a high school Latin enthusiast, aims to bridge the gap between
the original and the modern. WORDS is a large program, developed over two decades, and (re-)developing it from scratch
is not going to be an easy task. Nevertheless, I intend to slowly cover all the features offered by the original, while
also providing new capabilities which could prove useful to users or developers.

# Current status

As of this moment (17th of July 2021), the program supports:

* Fast search of words and inflections in the dictionary (completely goes through Ovid's *Metamorphoses* in ~900 ms)
  \[qu- pronouns are an exception]
* Handles u/v and i/j alternations without any issues
* [Uses JSON to handle dictionary and inflection data](https://github.com/Salihbasic/whitaker-words-jsonisator)

# Priority features

As of this moment (17th of July 2021), the priority features are:

* Handle qu- pronouns and enclitics
* Handle affixes
* Handle forms of the verb *esse*
* Handle uniques
* Implement the same display format for searches like in the original
* Implement the same user interface like in the original

# Planned features

These features are to be implemented after the priority features, sometime in the future.

* Allow filtering of words based on any criteria
* Implement creation of dictionaries for specific input, along with dictionary customisation
* Handle external dictionary files, either as replacement or supplements to the original dictionary file
* Statistical analysis of text based on dictionary data
* Statistical analysis of dictionary based on large amounts of textual data (or just scrape from Perseus \_()_/)
* Declension/conjugation tables for any given word
* Developer API
* Website and mobile app (far future)

# Help

This is essentially a one man project trying to recreate and improve a giant from the past. Any help
(code, bug reports, suggestions...) is more than welcome.