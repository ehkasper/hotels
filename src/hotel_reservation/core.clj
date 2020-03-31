(ns hotel-reservation.core)
(require '[clojure.string :as str])

(def lakewood
  { :name "Lakewood"
    :classification 3
    :weekdays       110
    :weekdaysReward 80
    :weekends       90
    :weekendsReward 80 })

(def bridgewood
  { :name "Bridgewood"
    :classification 4
    :weekdays       160
    :weekdaysReward 110
    :weekends       60
    :weekendsReward 50 })

(def ridgewood
  { :name "Ridgewood"
    :classification 5
    :weekdays       220
    :weekdaysReward 100
    :weekends       150
    :weekendsReward 40 })

(defn get-dates [line]
  (let [dates (get (re-find #"\w+\:\ (.*)" line) 1)]
    (str/split dates #", ")))

(defn is-valid-day [day]
  (let [date-pattern #"\d{1,2}\w+\d{4}\(\w{3,4}\)"]
    (re-matches date-pattern day)))

(defn get-client-type [line]
  (get (re-find #"(\w+):" line) 1))

(defn is-valid-client-type [client-type]
  (or (= client-type "Regular") (= client-type "Reward")))

(defn get-day [date]
  (let [day-pattern "\\((\\w+)\\)"]
    (get (re-find (re-pattern day-pattern) date) 1)))

(defn get-days [dates]
  (map #(get-day %) dates))

(defn is-weekday? [day]
  (if (or (= day "sat") (= day "sun")) false true))

(defn sum-of [hotel client-type days]
  (let [weekdayKey (if (= client-type "Regular") :weekdays :weekdaysReward)
        weekendKey (if (= client-type "Regular") :weekends :weekendsReward)]
    (reduce +
      (map #(get hotel (if (is-weekday? %) weekdayKey weekendKey)) days))))

(defn get-hotels [line]
  (let [client-type (get-client-type line)
        days (map #(get-day %) (get-dates line))]
    (map (fn [hotel] { :name            (get hotel :name)
                       :total           (sum-of hotel client-type days)
                       :classification  (get hotel :classification) })
         [lakewood bridgewood ridgewood]
    )))

(defn sort-hotels [hotels]
  (sort-by :total (sort-by :classification > hotels)))

(defn find-cheapest-hotel [line]
  (get (first (sort-hotels (get-hotels line))) :name))
