(ns kraken-cljs.utils.core)

(defmacro b
  ([f]
   `(sb.utils.core/benchmark  (fn  [] ~f)))

  ([msg f]
   `(sb.utils.core/benchmark ~msg  (fn  [] ~f))))

