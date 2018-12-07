#!/usr/bin/env boot

(defn parse-input []
  (->> (slurp "input1")
       (string/split-lines)))

(defn problem1 []
       (parse-input))

(defn -main [& args]
  (println (problem1)))
