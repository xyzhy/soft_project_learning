from flask import Flask, request, jsonify
import pandas as pd
import numpy as np

app = Flask(__name__)

@app.route('/check/excel', methods = ['POST'])
def check_excel():
    data = request.get_data()
    excel = pd.read_excel(data)    
    columns = excel.columns.values
    if columns[0] != '英文' or columns[1] != '词性: 词1,词2;' or len(columns) != 2:
        return jsonify({'code': 400, 'msg': '请检查Excel文件格式'})

    resp = []
    for english, means in excel.values.astype(str):
        resp.append({
            'english': english,
            'means': means,
            'wordBookId': str(request.args.get('wordBookId')),
        })

    return jsonify({'code': 200, 'data': resp})

app.run()