(ns kraken-cljs.utils.core
  (:require [cljs.nodejs :as node]))

(defn p "Prints given arguments, and then returns the last one"
  [& values]
  (.info js/console (apply pr-str values))
  (last values))

(defn benchmark
  ([f] (benchmark nil f)) 
  ([msg f]
   (let [start (.now js/Date)
         result (f)]
     (p (str (when msg (str msg ": ")) (- (.now js/Date) start) "ms"))
     result)))

