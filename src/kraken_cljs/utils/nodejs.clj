(ns kraken-cljs.utils.nodejs)

(defmacro require* [& librefs]
  (cons 'do
        (for [[lib _ ref] (map #(if (coll? %) % [% :as %])
                               librefs)]
          `(def ~(symbol ref) (node/require ~(str lib))))))

