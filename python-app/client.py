# client.py 

import sys
import os
import json
import requests
from PyQt5.QtWidgets import QApplication, QMainWindow, QVBoxLayout, QWidget, QPushButton, QTextEdit, QComboBox

API_BASE = os.getenv("F1_API_BASE", "http://127.0.0.1:5000")

class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("F1-WeatherRec")
        
        self.layout = QVBoxLayout()
        
        self.consulta_selector = QComboBox(self)
        self.consulta_selector.addItem("Consulta 1")
        self.consulta_selector.addItem("Consulta 2")
        self.consulta_selector.addItem("Consulta 3")
        self.layout.addWidget(self.consulta_selector)
        
        self.consult_button = QPushButton("Consultar", self)
        self.consult_button.clicked.connect(self.realizar_consulta)
        self.layout.addWidget(self.consult_button)
        
        self.resultado = QTextEdit(self)
        self.layout.addWidget(self.resultado)
        
        container = QWidget()
        container.setLayout(self.layout)
        self.setCentralWidget(container)
    
    def realizar_consulta(self):
        consulta = self.consulta_selector.currentText()
        if consulta == "Consulta 1":
            endpoint = "/consulta1"
        elif consulta == "Consulta 2":
            endpoint = "/consulta2"
        else:
            endpoint = "/consulta3"
        
        url = f"{API_BASE}{endpoint}"
        
        try:
            response = requests.get(url, timeout=20)
            response.raise_for_status()
            self.resultado.setPlainText(json.dumps(response.json(), indent=2, ensure_ascii=False))
        except Exception as e:
            self.resultado.setPlainText(f"Error en la consulta: {e}")

if __name__ == '__main__':
    app = QApplication(sys.argv)
    main_window = MainWindow()
    main_window.show()
    sys.exit(app.exec_())
