(ns kraken-cljs.utils.nodejs)

(defmacro require* [& librefs]
  (cons 'do
        (for [[lib _ ref] (map #(if (coll? %) % [% :as %])
                               librefs)]
          `(def ~(symbol ref) (node/require ~(str lib))))))

;;; DEPRECATED. Don't use node namespace, use js namespace.
(defmacro def-globals [globals]
  (cons 'do
        (map (fn [g] `(def ~(symbol g) (~(symbol "js*") ~g))) globals)))
(comment "question: how to escape the namespace for js*?"
         "~(symbol string) is really ugly.")

