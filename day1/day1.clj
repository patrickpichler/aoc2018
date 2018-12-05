#!/usr/bin/env boot

(require '[clojure.string :as string])

(defn problem1 []
  (->> (slurp "input1")
       (string/split-lines)
       (map #(Integer/parseInt %1))
       (reduce +)))

(defn -main [& args]
  (println (problem1)))
