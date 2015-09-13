(ns kraken-cljs.utils.core)

(defmacro b
  ([f]
   `(kraken-cljs.utils.core/benchmark  (fn  [] ~f)))

  ([msg f]
   `(kraken-cljs.utils.core/benchmark ~msg  (fn  [] ~f))))

