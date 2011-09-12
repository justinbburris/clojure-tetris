; I guess we're defining all of the app dependeices here to be loaded for the rest of the project
; -----
; I'm not totally sure why we state 1.0.0-SNAPSHOT - Perhaps as a means for defining a project version?
; Why include SNAPSHOT though?
; -----
; I suppose that the main portion references tetris/core.clj
(defproject tetris "1.0.0-SNAPSHOT"
  :description "Tetris implementation in clojure"
  :dependencies [	[org.clojure/clojure "1.2.0"]
									[org.clojure/clojure-contrib "1.2.0"]
									[deflayout "0.9.0-SNAPSHOT"]]
	:dev-dependencies [[swank-clojure "1.2.1"]]
	:main tetris.core
)