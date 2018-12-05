#!/usr/bin/env boot

(require '[clojure.string :as string])


(defn parse-input []
  (->> (slurp "input1")
       (string/split-lines)
       (map #(Integer/parseInt %1))))

(defn problem1 []
       (reduce + (parse-input)))

(defn problem2 []
  (->> (parse-input)
       (cycle)
       (reduce (fn [[f fs] c] 
                 (let 
                   [nf (+ f c)]
                      (if (contains? fs nf)
                        (reduced nf)
                        [nf (conj fs nf)])))
            [0 #{}])))

(defn -main [& args]
  (println (problem1))
  (println (problem2)))
