(ns status-im.ui.components.nfc
  (:require [status-im.utils.platform :as platform]))

(def android-only-error "NFC API is available only on Android")

(def nfc                    (js/require "nfc-react-native"))

(defn get-card-id [on-success on-error]
  (if platform/android?
    (-> (.getCardId nfc)
        (.then on-success)
        (.catch on-error))
    (on-error android-only-error)))

(defn read-tag [sectors on-success on-error]
  (if platform/android?
    (-> (.readTag nfc (clj->js sectors))
        (.then on-success)
        (.catch on-error))
    (on-error android-only-error)))

(defn write-tag [sectors card-id on-success on-error]
  (if platform/android?
    (-> (.writeTag nfc (clj->js sectors) card-id)
        (.then on-success)
        (.catch on-error))
    (on-error android-only-error)))
