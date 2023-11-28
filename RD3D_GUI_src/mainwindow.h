#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QFileDialog>
#include <QDir>
#include <QMessageBox>
#include <QProcess>
#include <QDesktopServices>
#include <QUrl>
#include "crystaladvanced.h"
#include "runwindow.h"
#include "inputedit.h"

#include <algorithm>
#include <string>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    //bool simulation;
    ~MainWindow();

private slots:
    void on_comboBox_activated(const QString &arg1);

    void on_pushButton_browsemodel_clicked();

    void on_pushButton_heavyProt_clicked();

    void on_comboBox_AbsCoef_activated(const QString &arg1);

    void on_pushButton_solventConc_clicked();

    void on_pushButton_browseFasta_clicked();

    void on_pushButton_CIF_clicked();

    void on_pushButton_crystalAdvanced_clicked();
    void receiveData(QString anglePIn, QString angleLIn, QString peEscapeIn, QString flEscapeIn, QString calcSurrIn, QString defineDensityIn, QString densityIn,
                     QString surrElementsIn, QString goniometerIn, QString polaristionIn, QString containerTypeIn, QString containerThicknessIn,
                     QString containerDensityIn, QString matieralMixtureIn, QString materialElementsIn);

    void on_pushButton_run_clicked();

    void on_comboBox_subprogram_activated(const QString &arg1);

    void saveValues();

    void writeInput(QString filename);

    void on_comboBox_beamType_activated(const QString &arg1);

    void on_comboBox_monochrome_activated(const QString &arg1);

    //void rightMessage();
    //void wrongMessage();

    void tokenize(std::string s, std::string del);
    void tokenize2(std::string s, std::string del, QString first);

    void populateParam(std::string input);

    void on_pushButton_manualEdit_clicked();

    void on_actionSave_triggered();

    void on_actionOpen_triggered();

    void on_actionOpen_2_triggered();

    void on_actionUser_guide_triggered();

    void on_actionClose_triggered();

    void on_actionAbout_triggered();

    void on_comboBox_collType_activated(const QString &arg1);

private:
    Ui::MainWindow *ui;
    CrystalAdvanced *crystaladvanced;
    CrystalAdvanced *crystaladvanced2;
    runWindow *runwindow;
    inputEdit *inputedit;
   // QProcess *proc;
    QString crystalType, dimX, dimY, dimZ, modelFile;
    QString absCoef, ppm;
    QString cellA, cellB, cellC, cellAlpha, cellBeta, cellGamma, numMonmomer, numResidue, heavyProt, solventConc,
            solventFrac, numDNA, numRNA, numCarb;
    QString pdb, fasta, cif, pConc;
    QString subprogram, photons, runs;
    QString angleP, angleL, peEscape, flEscape, calcSurr, defineDensity, density, surrElements, goniometer, polaristion,
            containerType, containerThickness, containerDensity, matieralMixture, materialElements;
    QString beamType, beamFWHMX, beamFWHMY, beamFile, beamPxSizeX, beamPxSizeY, beamCollType, beamCollX, beamCollY, beamFlux, beamEnergy, beamEnergyFWHM, beamIsMon;
    QString wedgeStart, wedgeEnd, expTime, angleRes, offsetX, offsetY, offsetZ, offsetRot, translationX, translationY, translationZ;

};

#endif // MAINWINDOW_H
