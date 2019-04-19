from flask import *
import MySQLdb
import os
from werkzeug.utils import secure_filename
con=MySQLdb.connect(host='localhost',user='root',passwd='root',port=3306,db='project_db')
cmd=con.cursor()
app=Flask(__name__)
path1="C:\\Users\\hp\\PycharmProjects\\User Centric Similarity Search\\similarity search\\static\\products"
path2="C:\\Users\\hp\\PycharmProjects\\User Centric Similarity Search\\similarity search\\static\\Prt_videos"
app.secret_key='sk'

@app.route('/login',methods=['POST','GET'])
def login():
    uname = request.args.get('username')
    passwd = request.args.get('pwd')
    try:
        cmd.execute("select * from login where username='" + uname + "' and password='" + passwd + "'")
        s = cmd.fetchone()
        if s[3] == 'user':
            id = s[0]
            return jsonify({'task': id})
    except Exception as e:
        print(str(e))
        return jsonify({'task': "invalid"})

@app.route('/Cust_reg',methods=['POST','GET'])
def Cust_reg():
    name=request.args.get('name')
    gender=request.args.get('gender')
    housename=request.args.get('hname')
    street=request.args.get('street')
    state=request.args.get('state')
    country=request.args.get('country')
    pincode=request.args.get('pincode')
    email=request.args.get('email')
    mob=request.args.get('mob')
    pswd=request.args.get('pswd')
    cmd.execute("insert into login value(null,'"+email+"','"+pswd+"','customer')")
    id=con.insert_id()
    cmd.execute("insert into customer_reg values('"+str(id)+"','"+name+"','"+gender+"','"+housename+"','"+street+"','"+state+"','"+country+"','"+pincode+"','"+mob+"')")
    con.commit()
    return jsonify({'task': "success"})

@app.route('/productview',methods=['GET'])
def productview():
    category=request.args.get('category')
    cmd.execute("select * from product_mgt where category='"+category+"'")
    row_headers = [x[0] for x in cmd.description]
    results = cmd.fetchall()
    json_data = []
    for result in results:
        json_data.append(dict(zip(row_headers, result)))
    con.commit()
    print(json_data)
    return jsonify(json_data)

@app.route('/orderprt',methods=['POST','GET'])
def orderprt():
    uid=request.args.get('uid')
    pid=request.args.get('itemname')
    billno = request.args.get('billno')
    qty=request.args.get('qty')
    price=request.args.get('price')
    cmd.execute("insert into orders values('null','"+uid+"','"+pid+"','"+billno+"','"+qty+"','"+price+"',curdate())")
    con.commit()
    return jsonify({'task': "success"})

@app.route('/review',methods=['POST','GET'])
def review():
    uid=request.args.get('uid')
    pid=request.args.get('pid')
    review=request.args.get('review')
    photo=request.files['files']
    img=secure_filename(photo.filename)
    photo.save(os.path.join(path1,img))
    rating=request.args.get('rate')
    cmd.execute("insert into review values('null','"+uid+"','"+pid+"','"+review+"','"+img+"','"+rating+"',curdate())")
    con.commit()
    return jsonify({'task': "success"})





if(__name__=='__main__'):
    app.run(debug=True)



