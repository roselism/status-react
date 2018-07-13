(ns status-im.ui.components.dialog)

(defn- callback [options]
  (fn [index]
    (when (< index (count options))
      (when-let [handler (:action (nth options index))]
        (handler)))))

(def dialogs (js/require "react-native-dialogs"))

(defn- show [{:keys [title options cancel-text]}]
  (let [dialog (new dialogs)]
    (.set dialog (clj->js {:title         title
                           :negativeText  cancel-text
                           :items         (mapv :label options)
                           :itemsCallback (callback options)}))
    (.show dialog)))
