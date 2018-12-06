#!/usr/bin/env boot

(require '[clojure.string :as string])


(defn parse-input []
  (->> (slurp "input")
       (string/split-lines)))

(defn count-frequencies []
 (->> (parse-input)
            (map frequencies)
            (map vals)
            (map set)
            (reduce (fn [[twos threes] s] 
                      (let [two (if (contains? s 2) 1 0)
                            three (if (contains? s 3) 1 0)]
                                [(+ twos two) (+ threes three)])) [0 0])))

(defn problem1 []
       (let [[twos threes] (count-frequencies)]
         (* twos threes)))

(defn -main [& args]
  (println (problem1)))
