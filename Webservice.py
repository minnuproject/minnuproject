from unicodedata import category

import numpy
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
    print(uname)
    print(passwd)
    try:
        cmd.execute("select * from login where username='" + uname + "' and password='" + passwd + "'")
        s = cmd.fetchone()
        if s[3] == 'user':
            id = s[0]
            session['uid']=id
            return jsonify({'task': id})

        elif s[3] == 'blocked':
            return jsonify({'task': "blocked"})

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
    cmd.execute("insert into login value(null,'"+email+"','"+pswd+"','user')")
    id=con.insert_id()
    cmd.execute("insert into customer_reg values('"+str(id)+"','"+name+"','"+gender+"','"+housename+"','"+street+"','"+state+"','"+country+"','"+pincode+"','"+email+"','"+mob+"')")
    con.commit()
    return jsonify({'task': "success"})

@app.route('/productview',methods=['GET','POST'])
def productview():
    category=request.args.get('category')
    print(category)
    # cmd.execute("SELECT `product_mgt`.*,`review`.`review` FROM `product_mgt` INNER JOIN `review` ON `product_mgt`.`pid`=`review`.`pid` where product_mgt.category='"+category+"'")
    cmd.execute("SELECT DISTINCT `product_mgt`.* FROM `product_mgt`  WHERE product_mgt.category='"+category+"'")
    print(category)
    row_headers = [x[0] for x in cmd.description]
    results = cmd.fetchall()
    json_data = []
    for result in results:
        json_data.append(dict(zip(row_headers, result)))
    con.commit()
    print(json_data)
    return jsonify(json_data)
@app.route('/viewReviewss',methods=['GET'])
def viewReviewss():
    pid=request.args.get('pid')
    print(pid)
    cmd.execute("SELECT `customer_reg`.`name`,`review`.`review` FROM `customer_reg` INNER JOIN `review` ON `customer_reg`.`uid`=`review`.`uid` WHERE `review`.`pid`='"+str(pid)+"'")
    print(category)
    row_headers = [x[0] for x in cmd.description]
    results = cmd.fetchall()
    print(results)
    json_data = []
    for result in results:
        json_data.append(dict(zip(row_headers, result)))
    con.commit()
    print(json_data)
    return jsonify(json_data)

@app.route('/reminder',methods=['GET','POST'])
def reminder():
    uid = request.form['uid']
    print(uid,"uiddddddddddd")
    cmd.execute("SELECT `product_mgt`.`item_name`,`product_mgt`.`category`,`review`.`review`,`customer_reg`.`name` FROM `product_mgt` INNER JOIN `orders` ON `orders`.`pid` = `product_mgt`.`pid` LEFT JOIN `review` ON `orders`.`uid` = `review`.`uid` AND `orders`.`pid` = `review`.`pid` INNER JOIN `customer_reg` ON `orders`.`uid` = `customer_reg`.`uid` AND `customer_reg`.`uid` = '"+uid+"'")
    # cmd.execute("SELECT `product_mgt`.`item_name`,`product_mgt`.`category` FROM `product_mgt` JOIN `orders` ON `orders`.`pid`=`product_mgt`.`pid` WHERE `orders`.`pid`!= (SELECT pid FROM `review` WHERE `pid`='14' AND `uid`='"+uid+"' ) AND `orders`.`uid`!=(SELECT uid FROM `review` WHERE `pid`='14' AND `uid`='"+uid+"')")
    row_headers = [x[0] for x in cmd.description]
    results = cmd.fetchall()
    json_data = []
    for result in results:
        json_data.append(dict(zip(row_headers, result)))
    con.commit()
    print(json_data)
    return jsonify(json_data)

@app.route('/offersview')
def offersview():
    category = request.args.get('category')
    pid = request.args.get('pid')
    cmd.execute("SELECT `offers`.*,`product_mgt`.`price`,`product_mgt`.`qty`,`product_mgt`.`description`,`product_mgt`.`photo_1`FROM `offers` INNER JOIN `product_mgt` ON `offers`.pid=`product_mgt`.pid WHERE `product_mgt`.`pid`='"+str(pid)+"'")
    row_headers = [x[0] for x in cmd.description]
    results = cmd.fetchall()
    json_data = []
    for result in results:
        json_data.append(dict(zip(row_headers, result)))
    con.commit()
    print(json_data)
    return jsonify(json_data)

@app.route('/sitenoti',methods=['GET','POST'])
def sitenoti():
    try:
        # print('haiiiiiiiiiiii')
        cmd.execute("SELECT `message`,`date` FROM site_notification")
        row_headers = ['message','date']
        results = cmd.fetchall()
        print(results)
        json_data = []
        for result in results:
            re=[]
            re.append(result[0])
            re.append(str(result[1]))
            json_data.append(dict(zip(row_headers, re)))
        con.commit()
        print(json_data)
        return jsonify(json_data)
    except Exception as e:
        print(e)





@app.route('/orderprt',methods=['POST','GET'])
def orderprt():
    uid=request.args.get('uid')
    pid=request.args.get('itemname')
    billno = request.args.get('billno')
    qty=request.args.get('qty')
    price=request.args.get('price')
    cmd.execute("SELECT * FROM stock_notification WHERE pid='"+str(pid)+"'")
    a=cmd.fetchone()
    print(a)
    cmd.execute("insert into orders values('null','"+uid+"','"+pid+"','"+billno+"','"+qty+"','"+price+"',curdate())")
    con.commit()
    return jsonify({'task': "success"})

@app.route('/rev',methods=['POST','GET'])
def rev():
    category = request.args.get('category')
    cmd.execute("select * from product_mgt where category='"+category+"'")
    row_headers = [x[0] for x in cmd.description]
    results = cmd.fetchall()
    json_data = []
    for result in results:
        json_data.append(dict(zip(row_headers, result)))
    con.commit()
    print(json_data)
    return jsonify(json_data)


@app.route('/reviews',methods=['POST','GET'])
def reviews():
    print("bb")

    uid = request.form['uid']

    pid = request.form['pid']
    review = request.form['review']
    import nltk
    from nltk.sentiment.vader import SentimentIntensityAnalyzer

    eid = SentimentIntensityAnalyzer()
    ss = eid.polarity_scores(review)
    pos = float(ss['pos'])
    neg = float(ss['neg'])
    diff = pos - neg
    print(diff)


    if diff < 0:

        cmd.execute("insert into blocking values(null,'"+pid+"','"+uid+"')")
        con.commit()

        cmd.execute(" SELECT COUNT(*) FROM `blocking` WHERE pid='"+str(pid)+"' AND uid='"+str(uid)+"'")

        res=cmd.fetchone()
        total=res[0]

        if total>3:
            cmd.execute("UPDATE `login` SET `type`='blocked' WHERE `id`='"+str(uid)+"'")
            con.commit()
            val={'task': "blocked"}
            res=json.dumps(val)
            return res

    try:

        review=request.form['review']
        rate = request.form['rate']
        photo=request.files['files']
        img=secure_filename(photo.filename)
        photo.save(os.path.join(path1,img))
        print(uid,pid,review,rate)
        cmd.execute("insert into review values(null,'"+uid+"','"+pid+"','"+review+"','"+img+"','"+rate+"',curdate())")

        con.commit()
        print("ok")
        # import nltk
        # from nltk.sentiment.vader import SentimentIntensiteAnalyzer
        #
        # eid=SentimentIntensiteAnalyzer()
        # ss=eid.polarity_scores(review)
        # pos=float( ss['pos'])
        # neg=float(ss['neg'])
        # diff=pos-neg
        # print(diff)

        print("UPDATE `product_mgt` SET `ranking` =`ranking`+ '"+str(diff)+"' WHERE `pid` = '"+str(pid)+"'")
        cmd.execute("UPDATE `product_mgt` SET `ranking` =`ranking`+ '"+str(diff)+"' WHERE `pid` = '"+str(pid)+"'")
        con.commit()
        val = {'task': "success"}
        res = json.dumps(val)
        return res


    except Exception as e:
        print(e)

@app.route('/ranking',methods=['POST','GET'])
def ranking():
    category = request.args.get('category')
    cmd.execute("SELECT * FROM `product_mgt` WHERE `category`='"+category+"' ORDER BY `ranking` DESC")
    row_headers = [x[0] for x in cmd.description]
    results = cmd.fetchall()
    json_data = []
    for result in results:
        json_data.append(dict(zip(row_headers, result)))
    con.commit()
    print(json_data)
    return jsonify(json_data)

@app.route('/similarProd',methods=['POST','GET'])
def similarProd():
        print("haiiiiiiiii")
        uid=request.form['uid']

        cmd.execute("SELECT DISTINCT `product_mgt`.`category`,`product_mgt`.`item_name`,`product_mgt`.`description`,`product_mgt`.`pid` FROM `product_mgt`  WHERE  `product_mgt`.`pid`  IN (SELECT `orders`.`pid` FROM `orders`  WHERE `orders`.uid='"+uid+"')")
        s=cmd.fetchall()
        print(s)
        mobile=[]
        tab=[]
        othermob=[]
        othermob1=[]
        othertab=[]
        othertab1=[]
        for d in s:
            descrip=d[2].split(',')
            if d[0]== "mobile":

                row=[]
                print(descrip)
                row.append(str(descrip[0]).split('GB')[0])
                row.append(str(descrip[1]).split('GB')[0])
                row.append(str((descrip[2]).split('cm')[0].replace('\n','')).replace('\r',''))

                cam=str(descrip[3]).replace('mp','').split(' ')

                cam=cam[0].split('|')
                fcam=0
                pcam=0
                if len(cam)!=1:
                    fcams=cam[1].split('+')
                    for v in fcams:
                        fcam=fcam+int(v)
                pcams=cam[0].split('+')
                for v in pcams:
                    pcam = pcam+int(v)
                row.append(pcam)
                row.append(fcam)
                row.append(str((descrip[4]).split('mAh')[0].replace('\n', '')).replace('\r', ''))
                mobile.append(row)
            else:
                row=[]
                print(descrip)
                row.append(str(descrip[0]).split('GB')[0])
                row.append(str(descrip[1]).split('GB')[0])
                row.append(str((descrip[2]).split('cm')[0].replace('\n', '')).replace('\r', ''))

                cam=str(descrip[3]).replace('mp','').split(' ')
                cam=cam[0].split('|')
                fcam=0
                pcam=0
                if len(cam)!=1:
                    fcams=cam[1].split('+')
                    for v in fcams:
                        fcam=fcam+float(v)
                pcams=cam[0].split('+')
                for v in pcams:
                    pcam=pcam+int(v)
                row.append(pcam)
                row.append(fcam)
                row.append(str((descrip[4]).split('mAh')[0].replace('\n', '')).replace('\r', ''))
                tab.append(row)
        con.commit()

        cmd.execute("SELECT DISTINCT `product_mgt`.`category`,`product_mgt`.`item_name`,`product_mgt`.`description`,`product_mgt`.`pid` FROM `product_mgt`  WHERE  `product_mgt`.`pid`  NOT IN (SELECT `orders`.`pid` FROM `orders`  WHERE `orders`.`uid`='"+uid+"')")
        b=cmd.fetchall()
        for d in b:
            if d[0] == "mobile":
                row = []

                descrip = d[2].split(',')
                print(descrip)
                row.append(str(descrip[0]).split('GB')[0])
                row.append(str(descrip[1]).split('GB')[0])
                row.append(str((descrip[2]).split('cm')[0].replace('\n', '')).replace('\r', ''))

                cam = str(descrip[3]).replace('mp', '').split(' ')

                cam = cam[0].split('|')
                fcam = 0
                pcam = 0
                if len(cam) != 1:
                    fcams = cam[1].split('+')
                    for v in fcams:
                        fcam = fcam+float(v)
                pcams = cam[0].split('+')
                for v in pcams:
                    try:
                        pcam = pcam+float(v)
                    except:
                        pass
                row.append(pcam)
                row.append(fcam)
                row.append(str((descrip[4]).split('mAh')[0].replace('\n', '')).replace('\r', ''))
                othermob.append(row)
                othermob1.append(d[3])
            else:
                row = []
                descrip = d[2].split(',')
                row.append(str(descrip[0]).split('GB')[0])
                row.append(str(descrip[1]).split('GB')[0])
                row.append(str(descrip[2]).split('cm')[0])

                cam = str(descrip[3]).replace('mp', '').split(' ')
                cam = cam[0].split('|')
                fcam = 0
                pcam = 0
                if len(cam) != 1:
                    fcams = cam[1].split('+')
                    for v in fcams:

                        fcam = fcam+float(v)
                pcams = cam[0].split('+')
                for v in pcams:
                    try:
                        pcam = pcam+float(v)
                    except:
                        pass
                row.append(pcam)
                row.append(fcam)
                row.append(str((descrip[4]).split('mAh')[0].replace('\n', '')).replace('\r', ''))
                othertab.append(row)
                othertab1.append(d[3])

        con.commit()

        import numpy as np
        from scipy.spatial import distance
        input_arr=(othermob)
        test_case=(mobile)

        print(test_case,"test case")
        print(input_arr,"input array")
        dst=[]
        for i in range(0,len(input_arr)):
            print((input_arr[i]),(test_case[0]))
            dist =0.0#  numpy.math.sqrt(sum([(len(input_arr[i]) - len(test_case[0])) ** 2 for (input_arr,test_case) in zip(input_arr, test_case)]))

            for j in range(0,len(input_arr[i])):
                dist=dist+((float(input_arr[i][j])-float(test_case[0][j]))*(float(input_arr[i][j])-float(test_case[0][j])))
                print(dist)
            import math

            dist1=math.sqrt(dist)

            dst.append(dist1)
            print(dist)
            print('-----------------------------------------------------------------------------------------------------')
        print(dst)
        i=0
        cp=0
        min=999999999.0
        for d in dst:
            if(d<min):
                min=d
                cp=i
            i=i+1
        pid="'0',"+"'"+str(othermob1[cp])+"'"
############################################################################################
        try:
            input_arr = (othertab)
            test_case = (tab)

            print(test_case, "test case")
            print(input_arr, "input array")
            dst = []
            for i in range(0, len(input_arr)):
                print((input_arr[i]), (test_case[0]))
                dist = 0.0  # numpy.math.sqrt(sum([(len(input_arr[i]) - len(test_case[0])) ** 2 for (input_arr,test_case) in zip(input_arr, test_case)]))

                for j in range(0, len(input_arr[i])):
                    dist = dist + (
                    (float(input_arr[i][j]) - float(test_case[0][j])) * (float(input_arr[i][j]) - float(test_case[0][j])))
                    print(dist)
                import math

                dist1 = math.sqrt(dist)

                dst.append(dist1)
                print(dist)
                print(
                    '-----------------------------------------------------------------------------------------------------')
            print(dst)
            i = 0
            cp = 0
            min = 999999999.0
            for d in dst:
                if (d < min):
                    min = d
                    cp = i
                i = i + 1
            pid = pid+",'"+str(othertab1[cp])+"'"
        except Exception as e:
            print(e)

        print("SELECT * FROM `product_mgt` where pid in ("+pid+")")
        cmd.execute("SELECT * FROM `product_mgt` where pid in ("+pid+")")
        row_headers = [x[0] for x in cmd.description]
        a = cmd.fetchall()
        print(a)
        con.commit()
        json_data = []
        for result in a:
            json_data.append(dict(zip(row_headers, result)))

        con.commit()
        print(json_data)
        return jsonify(json_data)


@app.route('/payment',methods=['Post','GET'])
def payment():
    account_name=request.args.get('acc_name')
    acct_no=request.args.get('acc_no')
    valid=request.args.get('valid')
    ccv=request.args.get('ccv')
    amount=request.args.get('amount')

    uid=request.args.get('uid')

    pid = request.args.get('pid')
    qty = request.args.get('qty')

    cmd.execute("SELECT *FROM `bank` WHERE `acct_no` = '"+acct_no+"' AND `ccv` = '"+ccv+"'")
    f=cmd.fetchone();
    if f is not None:
            blc=f[5]
            if float(blc) > float(amount) :

                cmd.execute("SELECT `balance` FROM `bank` WHERE `acct_no` = '20321628942375'")
                m=cmd.fetchone()

                if m is not None:
                    bc=m[0]
                    newblc=float(bc)+float(amount)
                    print(acct_no)
                    cmd.execute("UPDATE `bank` SET `balance` = '"+str(newblc)+"' WHERE `acct_no` = '20321628942375'")
                    usrblc=float(blc)-float(amount)
                    cmd.execute("UPDATE `bank` SET `balance` = '"+str(usrblc)+"' WHERE `acct_no` = '"+acct_no+"'")
                    cmd.execute("INSERT `payment` VALUES(null,'"+str(uid)+"','DebitCard','paid')")
                    bill_no=con.insert_id()
                    con.commit()
                    cmd.execute("INSERT INTO `orders` VALUES(NULL,'"+str(uid)+"','"+str(pid)+"','"+str(bill_no)+"','"+qty+"','"+amount+"',CURDATE())")
                    con.commit()

                    return jsonify({'result': "ok"})

            else:

                return jsonify({'result': "no"})

    else:

        return jsonify({'result': "invalid"})



@app.route('/cashondelivery',methods=['Post','GET'])
def cashondelivery():
    amount = request.args.get('amount')
    uid = request.args.get('uid')
    pid = request.args.get('pid')
    qty = request.args.get('qty')
    cmd.execute("INSERT `payment` VALUES(NULL,'"+str(uid)+"','cashOnDelivery','pending')")
    bill_no=con.insert_id()
    con.commit()
    cmd.execute("INSERT INTO `orders` VALUES(NULL,'" + str(uid) + "','" + str(pid) + "','" + str(bill_no) + "','" + qty + "','" + amount + "',CURDATE())")
    con.commit()
    return jsonify({'result': "ok"})


if(__name__=='__main__'):
    app.run(host='192.168.43.211',port=5000)