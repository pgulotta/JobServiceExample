import QtQuick 2.15
import QtQuick.Window 2.15
import QtQuick.Controls 2.15

Window {
    visible: true
    width: 640
    height: 480
    title: qsTr("Job Service Scheduler")
    color: "LIGHTBLUE"

    Column {
        topPadding: 24
        spacing: 48
        anchors.horizontalCenter: parent.horizontalCenter
        ButtonGroup {
            id: buttonGroupId
        }
        ListView {
            id: scheduleSelectorId
            implicitWidth: 200
            implicitHeight: 200
            interactive: true
            leftMargin: 24
            model: scheduleModelId
            header: Label {
                text: qsTr("Recording Interval")
                font.pointSize: 14
                font.italic: true
                color: "BLUE"
            }
            delegate: RadioDelegate {
                id: radioDelegateId
                indicator: Rectangle {
                    implicitWidth: 24
                    implicitHeight: 24
                    x: radioDelegateId.width - width - radioDelegateId.rightPadding
                    y: parent.height / 2 - height / 2
                    radius: 12
                    color: "TRANSPARENT"
                    border.color: radioDelegateId.down ? "NAVY" : "ROYALBLUE"

                    Rectangle {
                        width: 16
                        height: 16
                        x: 4
                        y: 4
                        radius: 8
                        visible: radioDelegateId.checked
                        color: radioDelegateId.down ? "TRANSPARENT" : "MIDNIGHTBLUE"
                    }
                }
                background: Rectangle {
                    implicitWidth: 100
                    implicitHeight: 38
                    //visible: radioDelegateId.down || radioDelegateId.highlighted
                    color: radioDelegateId.down ? "CORNFLOWERBLUE" : "TRANSPARENT"
                }
                text: model.name
                font.pointSize: 14
                checked: model.itemIndex === scheduleSelectorId.currentIndex
                focusPolicy: Qt.StrongFocus
                ButtonGroup.group: buttonGroupId
                onClicked: if (checked) {
                           scheduleSelectorId.currentIndex = model.itemIndex
                           }
            }
        }
        Button {
            text: qsTr("Apply")
            anchors.horizontalCenter: parent.horizontalCenter
            onClicked:{
                Controller.scheduleJobService(scheduleModelId.get(scheduleSelectorId.currentIndex).intervalMS)
            }
        }
        Button {
            text: qsTr("Quit")
            anchors.horizontalCenter: parent.horizontalCenter
            onClicked:{
                Qt.quit()
            }
        }
        ListModel {
            id: scheduleModelId
            ListElement {
                name: qsTr("Never")
                itemIndex: 0
                intervalMS: 0
            }
            ListElement {
                name: qsTr("Every 15 Minutes")
                itemIndex: 1
                intervalMS: 900000
            }
            ListElement {
                name: qsTr("Hourly")
                itemIndex: 2
                intervalMS: 3600000
            }
            ListElement {
                name: qsTr("Daily")
                itemIndex: 3
                intervalMS: 86400000
            }
        }
    }
}
