#pragma once

#include <QObject>

class FrontController final : public QObject
{
    Q_OBJECT
public:
    explicit FrontController(QObject *parent = nullptr);

signals:

public:
    Q_INVOKABLE void scheduleJobService(int intervalinMS);

};


