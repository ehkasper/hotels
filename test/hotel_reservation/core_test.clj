(ns hotel-reservation.core-test
  (:require [clojure.test :refer :all]
            [hotel-reservation.core :refer :all]))

(deftest hotel-structures
  (testing "hotel objects"
           (lakewood { :classification  3
                       :weekdays        110
                       :weekdaysReward  80
                       :weekends        90
                       :weekendsReward  80 })
           (bridgewood { :classification  4
                         :weekdays        160
                         :weekdaysReward  110
                         :weekends        60
                         :weekendsReward  50 })
           (ridgewood { :classification   5
                        :weekdays         220
                        :weekdaysReward   100
                        :weekends         150
                        :weekendsReward   40 })
   ))

(deftest parsing-input
  (testing "should return true when input is a weekday"
    (is (is-weekday "mon"))
    (is (is-weekday "tues"))
    (is (is-weekday "wed"))
    (is (is-weekday "thu"))
    (is (is-weekday "fri")))
  (testing "should return false when input is a weekend"
    (not (is-weekday "sat")))
    (not (is-weekday "sun")))

(deftest test-date-format
  (testing "simple format"
         (is (= (.format
           (java.text.SimpleDateFormat. "dd.MM.yyyy")
           (.parse
             (java.text.SimpleDateFormat. "ddMMyyyy")
             "08082013")) "08.08.2013"))
           ;(= (.parse (str-to-date-formater) "20Mar2019") (java.time.LocalDateTime/of 2019 3 20 0 0))
           ))

(deftest test-dates
  (testing "testing date formats"
    (is (= (java.time.LocalDateTime/of 2019 11 27 0 0) (java.time.LocalDateTime/of 2019 11 27 0 0)))))
