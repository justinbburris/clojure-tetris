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

; Grabs a cell at a specified x y location
; it seems we're passing an x & y coordinate
; We then use get-in(?) to extract what is in the figure array at that location
(defn pick-cell [figure x y]
	(get-in figure [y x])
)

; This apparently acts like map but for a matrix
; I assume that we're iterating over rows (y) and then each column (x) for a row
; It looks like we then apply a function passed in via func to the element (e1) at a particular x y coordinate
; I assume vect is the column at the y location
(defn mapmatrix [func matrix]
	(into [] (map-indexed (fn[y vect]
													(into [] (map-indexed (fn[x e1]
																									(func e1 x y))
																								vect)))
												matrix)))

; Rotation of a tetrimino
; A figure (from figures I suppose) is passed into the function
; Then the size of the matrix for the figure is calculated
; We then use the mapmatrix function to apply the pick-cell function to fig, where upon we do some 
; modulus operation to the size?
; Not sure what #() is
(defn rotate-figure [fig]
	(let [fsize (count fig)]
		(mapmatrix #(pick-cell fig (- fsize %3 1) %2) fig)))
		

(defn apply-fig [glass fig [figx figy]]
	(let [fsize (count fig)]
		(mapmatrix (fn[e1 gx gy]
								(if (and
											(<= figx gx (+ figx fsize -1))
											(<= figy gy (+ figy fsize -1)))
										(+ e1 (pick-cell fig (- gx figx)(- gy figy)))
										e1))
		glass)))