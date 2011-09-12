; Okay definately java. Woah. Unexpected in this sort of environment.

; More import statements - Looks like we have (namespace?) level depenedencies
; Not sure what the exact difference is between import and use.
; I guess use is for the things we included in the project clj?

; gen-class?

; import-static?

(ns tetris.core
	(:import 
		(java.awt Color Dimension BorderLayout)
		(javax.swing JPanel JFrame JOptionPane JButton JLabel)
		(javax.awt.event KeyListener)
	)
	(:use
		clojure.contrib.import-static deflayout core
		clojure.contrib.swing-utils
	)
	(:gen-class)
)

(import-static java.awt.event.KeyEvent VK_LEFT VK_RIGHT VK_DOWN VK_UP VK_SPACE)

; Tetris implementation
(def empty-cell 0)
(def moving-cell 1)
(def filled-cell 2)
(def glass-width 10)
(def glass-height 20)
(def zero-cords [3 0]) ; x,y

; Tetris blocks 
(def stick[	[0 0 0 0]
						[1 1 1 1]
						[0 0 0 0]
						[0 0 0 0]])
						
(def square[[1 1]
						[1 1]])

(def tblock[[0 0 0]
						[1 1 1]
						[0 1 0]])

(def sblock [	[0 1 0]
							[0 1 1]
							[0 0 1]])

(def zblock [	[0 0 1]
							[0 1 1]
							[0 1 0]])

(def lblock [	[1 1 0]
							[0 1 0]
							[0 1 0]])

(def jblock [	[0 1 1]
							[0 1 0]
							[0 1 0]])
							
(def figures [stick square tblock sblock zblock lblock jblock])

; Okay so we're defining create-vector
; Not sure what comp is...
; vec is the vector we're creating
; repeat is the (atom?) we're repeating
(def create-vector (comp vec repeat))

; I believe this creates the glass with all empty cells
(def create-glass[]
	(create-vector glass-height
								(create-vector glass-width empty-cell)
	)
)