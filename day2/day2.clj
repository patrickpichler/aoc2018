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

(defn diff-by-word [w1 w2]
  (->> (map vector w1 w2)
       (filter #(not= (first %1) (last %1)))
       (count)))

(defn is-diff-by-one? [w1 w2]
  (= (diff-by-word w1 w2) 1))

(defn search-for-one-difference [id ids]
  (filter (partial is-diff-by-one? id) ids))

(defn get-equal-chars [w1 w2]
  (->> (map vector w1 w2)
       (filter #(= (first %1) (last %1)))
       (map first)
       (apply str)))

(defn find-id-one-difference [id ids]
  (let [[result] (search-for-one-difference id ids)]
    (if (nil? result) (recur (first ids) (rest ids))
      (get-equal-chars result id))))

(defn problem2 []
  (let [[head & tail] (parse-input)]
    (find-id-one-difference head tail)))

(defn -main [& args]
  (println (problem1))
  (println (problem2)))
