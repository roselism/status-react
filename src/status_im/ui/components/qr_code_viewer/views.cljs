(ns status-im.ui.components.qr-code-viewer.views
  (:require [reagent.core :as reagent]
            [status-im.ui.components.qr-code-viewer.styles :as styles]
            [status-im.ui.components.react :as react]
            [status-im.ui.components.text :as text]))

(def qr-code1                (js/require "react-native-qrcode"))

(defn qr-code [props]
  (reagent/create-element
   qr-code1
   (clj->js (merge {:inverted true} props))))

(defn- footer [style value]
  [react/view styles/footer
   [react/view styles/wallet-info
    [text/selectable-text {:value value
                           :style (merge styles/hash-value-text style)}]]])

(defn qr-code-viewer [{:keys [style hint-style footer-style]} value hint legend]
  {:pre [(not (nil? value))]}
  (let [{:keys [width height]} (react/get-dimensions "window")]
    [react/view {:style (merge styles/qr-code style)}
     [react/text {:style (merge styles/qr-code-hint hint-style)}
      hint]
     (when width
       (let [size (int (* 0.7 (min width height)))]
         [react/view {:style               (styles/qr-code-container size)
                      :accessibility-label :qr-code-image}
          [qr-code {:value value
                    :size  (- size (* 2 styles/qr-code-padding))}]]))
     [footer footer-style legend]]))
