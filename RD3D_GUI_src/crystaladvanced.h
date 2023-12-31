#ifndef CRYSTALADVANCED_H
#define CRYSTALADVANCED_H

#include <QDialog>

namespace Ui {
class CrystalAdvanced;
}

class CrystalAdvanced : public QDialog
{
    Q_OBJECT

public:
    explicit CrystalAdvanced(QWidget *parent = 0);
    ~CrystalAdvanced();
    void updateData(QString anglePIn, QString angleLIn, QString peEscapeIn, QString flEscapeIn, QString calcSurrIn, QString defineDensityIn, QString densityIn,
                    QString surrElementsIn, QString goniometerIn, QString polaristionIn, QString containerTypeIn, QString containerThicknessIn,
                    QString containerDensityIn, QString matieralMixtureIn, QString materialElementsIn);
    void updateData2(bool loadContainer, bool loadPE, bool loadFL, bool loadSurr);
    void appendMaterialEl(QString materialElementsIn);
    void appendSurroundingEl(QString surrElementsIn);
    void clearMaterialEl();
    void clearSurroundingEl();

private slots:
    void on_pushButton_clicked();

    void on_comboBox_containerType_activated(const QString &arg1);

    void on_comboBox_Density_activated(const QString &arg1);

    void on_comboBox_PE_activated(const QString &arg1);

    void on_pushButton_SurrEl_clicked();

    void on_pushButton_container_clicked();

    void on_comboBox_Surrounding_activated(const QString &arg1);



private:
    Ui::CrystalAdvanced *ui;
signals:
    void sendData(QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString, QString);
};

#endif // CRYSTALADVANCED_H
