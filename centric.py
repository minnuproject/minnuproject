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
@app.route('/')
def home():
    return render_template('Home_Page.html')


@app.route('/login')
def login():
    return render_template('Login.html')
@app.route('/log',methods=['POST','GET'])
def log():
    Uname=request.form['textfield']
    pswd=request.form['textfield2']
    cmd.execute("select * from login where username='"+Uname+"' and password='"+pswd+"'")
    s=cmd.fetchone()
    if s is None:
        return '''<script>alert('invalid');window.location="/"</script>'''
    else:
        if s[3]=='appowner':
            return render_template('App_Owner_Home.html')
        elif s[3]=='sellers':
            id=s[0]
            session['sid']=id

            return render_template('Sellers_Home.html')
        else:
            return '''<script>alert('invalid');window.location="/"</script>'''

@app.route('/logout')
def logout():
    return render_template('Login.html')



@app.route('/AppOwner_Home')
def AppOwner_Home():
    return render_template('App_Owner_Home.html')


@app.route('/Sellers_Manage')
def Sellers_Manage():
    cmd.execute("select * from sellers_reg")
    sel = cmd.fetchall()
    return render_template('Sellers_Manage.html',val=sel)
@app.route('/approve')
def approve():
    id=request.args.get('id')
    cmd.execute("update login set type='sellers' where id='"+str(id)+"'")
    con.commit()
    return '''<script>alert('approved');window.location="/Sellers_Manage"</script>'''
@app.route('/reject')
def reject():
    id=request.args.get('id')
    cmd.execute("update login set type='reject' where id='"+str(id)+"'")
    con.commit()
    return '''<script>alert('rejected');window.location="/Sellers_Manage"</script>'''


@app.route('/Customer_Feedback')
def Customer_Feedback():
    cmd.execute("SELECT `review`.*,`customer_reg`.`name`,`product_mgt`.`item_name` FROM `review` INNER JOIN `customer_reg` ON `review`.`uid`=`customer_reg`.`uid` INNER JOIN `product_mgt` ON `review`.`pid`=`product_mgt`.`pid`")
    feed=cmd.fetchall()
    return render_template('Customer_feedback.html',val=feed)
@app.route('/del_feed')
def del_feed():
    id=request.args.get('id')
    cmd.execute("delete from review where rid='"+str(id)+"'")
    con.commit()
    return '''<script>alert('deleted');window.location="/Customer_Feedback"</script>'''


@app.route('/Site_Notification')
def Site_Notification():
    return render_template('Site_Notification.html')
@app.route('/site_noti',methods=['POST','GET'])
def site_noti():
    btnclk=request.form['button']
    if(btnclk=="Submit"):
        message = request.form['textfield']
        cmd.execute("insert into site_notification values(null,'" + message + "',curdate())")
        con.commit()
        return '''<script>alert('submitted');window.location="Site_Notification"</script>'''
    else:
        return render_template('App_Owner_Home.html')


@app.route('/block_users')
def block_users():
    return render_template('Block_Users.html')


@app.route('/View_Offers')
def View_Offers():
    cmd.execute("SELECT DISTINCT category FROM  product_mgt ")
    s=cmd.fetchall()
    # print(s)
    return render_template('View_Offers.html',val=s)


@app.route('/index3', methods=['POST'])
def index3():
    data = request.form['de']
    print(data)
    cmd.execute("SELECT pid,`item_name` FROM `product_mgt` WHERE `category`='"+data+"'")
    s=cmd.fetchall()
    re=['0','Select']
    for d in s:
        re.append(d[0])
        re.append(d[1])
    resp = make_response(jsonify( re))
    resp.status_code = 200
    resp.headers['Access-Control-Allow-Origin'] = '*'
    return resp

@app.route('/View_OffItem')
def View_OffItem():
    cmd.execute("select * from offers")
    ot=cmd.fetchall()
    return render_template('View_Offers.html',val=ot)

@app.route('/btn_offerview',methods=['POST','GET'])
def btn_offerview():
    Category=request.form['cat']
    pid=request.form['item']
    cmd.execute("select * from offers where pid='"+pid+"'")
    s=cmd.fetchall()
    return render_template('View_Offers.html',btn=s)

@app.route('/Off_Item')
def Off_item():
    id=request.args.get('id')
    cmd.execute("select * from product_mgt where pid='"+str(id)+"'")
    s=cmd.fetchone()
    return render_template('View_Offer_Item.html',val=s)


@app.route('/View_Stock_Noti')
def View_Stock_Noti():
    cmd.execute("SELECT DISTINCT category FROM  product_mgt")
    s=cmd.fetchall()
    return render_template('View_Stock_Notification.html',val=s)

@app.route('/index4',methods=['POST'])
def index4():
    data=request.form['sn']
    print(data)
    cmd.execute("SELECT pid,item_name FROM product_mgt WHERE category='"+data+"'")
    t=cmd.fetchall()
    sg=['0','select']
    for d in t :
        sg.append(d[0])
        sg.append(d[1])
    resp=make_response(jsonify(sg))
    resp.status_code=200
    resp.headers['Access-Control-Allow-Origin']='*'
    return resp
@app.route('/Ptrview',methods=['POST','GET'])
def Ptrview():
    pid=request.form['item']
    cmd.execute("SELECT DISTINCT `stock_notification`.`stk_id`,`product_mgt`.`item_name`,`stock_notification`.`stock` FROM `product_mgt` INNER JOIN `stock_notification` ON `product_mgt`.`pid`=`stock_notification`.pid WHERE `stock_notification`.`pid`='"+str(pid)+"'")
    bs=cmd.fetchall()
    print(bs)
    return render_template('View_Stock_Notification.html',val=bs)


# Module-2


@app.route('/Sellers_Home')
def Sellers_Home():
    return render_template('Sellers_Home.html')

@app.route('/Sellers_Reg')
def Sellers_Reg():
    return render_template('Sellers_Reg.html')
@app.route('/S_regi',methods=['POST','GET'])
def S_regi():
    Name=request.form['textfield']
    Comp_Name=request.form['textfield2']
    Build_No=request.form['textfield3']
    City=request.form['textfield4']
    State=request.form['textfield5']
    Country=request.form['textfield6']
    Pincode=request.form['textfield7']
    Email=request.form['textfield8']
    Mob=request.form['textfield9']
    Officecode=request.form['select']
    office_no=request.form['textfield10']
    Passsword=request.form['textfield11']
    num=str(Officecode)+str(office_no)
    cmd.execute("insert into login values(null,'"+Email+"','"+ Passsword+"','pending')")
    id=con.insert_id()
    cmd.execute("insert into sellers_reg values('"+str(id)+"','"+Name+"','"+Comp_Name+"','"+Build_No+"','"+ City+"','"+State+"','"+Country+"','"+Pincode+"','"+Email+"','"+Mob+"','"+ num+"')")
    con.commit()
    return '''<script>alert("inserted successfully");window.location="/"</script>'''


@app.route('/Sel_Product_mgt')
def Sel_Product_mgt():
    cmd.execute("select * from product_mgt")
    pmt=cmd.fetchall()
    return render_template('Sel_Product_Management.html',val=pmt)
@app.route('/Pt_Update_view')
def Pt_Update_view():
    id=request.args.get('id')
    session['idd']=id
    cmd.execute("select * from product_mgt where pid='"+str(id)+"'")
    ss=cmd.fetchone()
    return render_template("Sel_Update_Product.html",val=ss)
@app.route('/Pt_Update_view1')
def Pt_Update_view1():
    Id=session['idd']
    cmd.execute("select * from product_mgt where pid='"+str(Id)+"'")
    ss=cmd.fetchone()
    return render_template("Sel_Update_Product.html",val=ss)

@app.route('/Update_ptr',methods=['POST','GET'])
def Update_ptr():
    id=session['idd']
    Category=request.form['textfield4']
    Item_name=request.form['textfield']
    Price=request.form['textfield2']
    Qty=request.form['textfield3']
    Description=request.form['textarea']
    cmd.execute("update product_mgt set category='"+Category+"',item_name='"+Item_name+"',price='"+Price+"',qty='"+Qty+"',description='"+Description+"' where pid='"+str(id)+"'")
    con.commit()
    return '''<script>alert('updated');window.location="/Pt_Update_view"</script>'''

@app.route('/img1_uld',methods=['POST','GET'])
def img1_uld():
    id=request.args.get('id')
    session['pp']=id
    return render_template("Prt_Uploadpht1.html")
@app.route('/Prt_Uploadpht1',methods=['POST','GET'])
def Prt_Uploadpht1():
    id=session['pp']
    Photo1 = request.files['fileField']
    img = secure_filename(Photo1.filename)
    Photo1.save(os.path.join(path1, img))
    cmd.execute("update product_mgt set photo_1='"+img+"' where pid='"+str(id)+"'")
    con.commit()
    return redirect('Pt_Update_view1')

@app.route('/img2_uld',methods=['POST','GET'])
def img2_uld():
    id=request.args.get('id')
    session['pr']=id
    return render_template("Prt_UploadPht2.html")
@app.route('/Prt_UploadPht2',methods=['POST','GET'])
def Prt_UploadPht2():
    id=session['pr']
    Photo2 = request.files['fileField']
    img1 = secure_filename(Photo2.filename)
    Photo2.save(os.path.join(path1, img1))
    cmd.execute("update product_mgt set photo_2='"+img1+"' where pid='"+str(id)+"'")
    con.commit()
    return redirect('Pt_Update_view1')

@app.route('/Video_uld',methods=['POST','GET'])
def Video_uld():
    id=request.args.get('id')
    session['vl']=id
    return render_template("Prt_VideoUpload.html")
@app.route('/Prt_VideoUpload',methods=['POST','GET'])
def Prt_VideoUpload():
    id=session['vl']
    Video = request.files['fileField']
    vlc = secure_filename(Video.filename)
    Video.save(os.path.join(path2, vlc))
    cmd.execute("update product_mgt set video='"+vlc+"' where pid='"+str(id)+"'")
    con.commit()
    return redirect('Pt_Update_view1')

@app.route('/Delete_prt')
def Delete_prt():
    id = request.args.get('id')
    cmd.execute("delete from product_mgt where pid='"+str(id)+"'")
    con.commit()
    return '''<script>alert("Deleted");window.location='/Sel_Product_mgt'</script>'''

@app.route('/Sel_Add_Product',methods=['POST','GET'])
def Sel_Add_Product():
    return render_template('Sel_Add_Product.html')
@app.route('/Sel_Addprt',methods=['POST','GET'])
def Sel_Addprt():
    Category=request.form['textfield4']
    Item_name=request.form['textfield']
    Price=request.form['textfield2']
    Qty=request.form['textfield3']
    Description=request.form['textarea']
    Photo1=request.files['fileField']
    img=secure_filename(Photo1.filename)
    Photo1.save(os.path.join(path1,img))
    Photo2=request.files['fileField2']
    img1=secure_filename(Photo2.filename)
    Photo2.save(os.path.join(path1,img1))
    Video=request.files['fileField3']
    vlc=secure_filename(Video.filename)
    Video.save(os.path.join(path2,vlc))

    cmd.execute("insert into product_mgt values(null,'"+Category+"','"+Item_name+"','"+Price+"','"+Qty+"','"+Description+"','"+img+"','"+img1+"','"+vlc+"','0')")
    id=con.insert_id()
    sid=session['sid']
    cmd.execute("insert into sel_product values(null,'"+str(id)+"','"+str(sid)+"')")
    con.commit()
    return '''<script>alert("inserted successfully");window.location="Sel_Product_mgt"</script>'''

@app.route('/Offers_Management')
def Offers_Management():
    cmd.execute("SELECT offers.*,product_mgt.item_name,product_mgt.price FROM offers INNER JOIN product_mgt ON offers.pid=product_mgt.pid")
    of=cmd.fetchall()
    return render_template('Sel_Offers_Management.html',val=of)
@app.route('/off_mgt',methods=['POST','GET'])
def off_mgt():
    return render_template('Sel_Add_Offers.html')
@app.route('/del_offer')
def del_offer():
    id=request.args.get('id')
    cmd.execute("delete from offers where off_no='"+str(id)+"'")
    con.commit()
    return '''<script>alert("deleted");window.location='/Offers_Management'</script>'''

@app.route('/Add_Offers',methods=['POST','GET'])
def Add_Offers():
    cmd.execute("SELECT DISTINCT category FROM  product_mgt")
    ao=cmd.fetchall()
    return render_template('Sel_Add_Offers.html',val=ao)

@app.route('/index5',methods=['POST'])
def index5():
    data=request.form['de']
    print(data)
    cmd.execute("SELECT pid,`item_name` FROM `product_mgt` WHERE `category`='"+data+"'")
    s=cmd.fetchall()
    re=['0','select']
    for d in s:
        re.append(d[0])
        re.append(d[1])
    resp=make_response(jsonify(re))
    resp.status_code=200
    resp.headers['Access-Control-Allow-Origin'] = '*'
    return resp

@app.route('/Add_Off',methods=['POST','GET'])
def Add_Off():
    pid=request.form['item']
    Offers=request.form['textarea']
    frm_date=request.form['textfield3']
    to_date=request.form['textfield4']
    New_Price=request.form['textfield5']
    cmd.execute("insert into offers values(null,'"+pid+"','"+ Offers+"','"+frm_date+"','"+ to_date+"','"+New_Price+"')")
    con.commit()
    return '''<script>alert("Inserted");window.location='/Add_Offers'</script>'''


@app.route('/Update_Offers',methods=['POST','GET'])
def Update_Offers():
    id = request.args.get('id')
    session['id']=id
    cmd.execute("select * from offers where off_no='"+str(id)+"'")
    t=cmd.fetchone()
    return render_template('Sel_Update_Offers.html',val=t)

@app.route('/Upt_off',methods=['POST','GET'])
def Upt_off():
    id=session['id']
    Offers=request.form['textarea']
    frm_date=request.form['textfield3']
    to_date=request.form['textfield4']
    New_Price=request.form['textfield5']
    cmd.execute("update offers set offers='"+Offers+"',from_date='"+frm_date+"',to_date='"+to_date+"',new_price='"+New_Price+"' where off_no='"+str(id)+"'")
    con.commit()
    return '''<script>alert("updated successfully");window.location="Update_Offers"</script>'''


@app.route('/Sel_CustomerOrder')
def Sel_CustomerOrder():
    cmd.execute("SELECT orders.*,customer_reg.name FROM orders JOIN customer_reg ON orders.uid=customer_reg.uid")
    cto=cmd.fetchall()
    return render_template('Sel_CustomerOrder.html',val=cto)
@app.route('/View_Orderitem',methods=['POST','GET'])
def View_Orderitem():
    id=request.args.get('id')
    cmd.execute("select * from product_mgt where pid='"+str(id)+"'")
    s=cmd.fetchone()
    return render_template('Sel_Order_Item_view.html',val=s)


@app.route('/Check_CustReview')
def Check_CustReview():
    cmd.execute("SELECT review.*,customer_reg.name,product_mgt.item_name FROM review INNER JOIN customer_reg ON review.uid=customer_reg.uid INNER JOIN product_mgt ON review.pid=product_mgt.pid")
    ccr=cmd.fetchall()
    return render_template('Sel_CheckingCust_Review.html',val=ccr)

@app.route('/delete_CustReview',methods=['get','post'])
def delete_CustReview():
    id=request.args.get('id')
    cmd.execute("delete from review where rid='"+str(id)+"'")
    con.commit()
    return '''<script>alert("deleted");window.location='/Check_CustReview'</script>'''

@app.route('/Sel_SiteView')
def Sel_SiteView():
    cmd.execute("select * from site_notification")
    sit=cmd.fetchall()
    return render_template('Sel_SiteNoti_View.html',val=sit)


@app.route('/Sel_StockNoti')
def Sel_StockNoti():
    cmd.execute("SELECT DISTINCT category FROM  product_mgt")
    sn=cmd.fetchall()
    return render_template('Sel_Stock_Notification.html',val=sn)

@app.route('/index6',methods=['POST'])
def index6():
    data=request.form['sn']
    print(data)
    cmd.execute("SELECT pid,item_name FROM product_mgt WHERE category='"+data+"'")
    s=cmd.fetchall()
    sn=['0','select']
    for d in s:
        sn.append(d[0])
        sn.append(d[1])
    resp=make_response(jsonify(sn))
    resp.status_code=200
    resp.headers['Access-Control-Allow-Origin'] = '*'
    return resp

@app.route('/stknoti',methods=['POST','GET'])
def stknoti():
     b1=request.form['button']

     if b1=='Search':
        pid = request.form['item']
        cmd.execute("SELECT DISTINCT stock_notification.stk_id,product_mgt.item_name,stock_notification.`stock` FROM `stock_notification` INNER JOIN `product_mgt` ON `product_mgt`.`pid`=`stock_notification`.`pid` WHERE `stock_notification`.`sid` and product_mgt.pid='" + pid + "'")
        stk = cmd.fetchall()
        print(stk)
        return render_template('Sel_Stock_Notification.html', val=stk)
     else:
       return redirect('Add_Stock')


@app.route('/Add_Stock',methods=['POST','GET'])
def Add_Stock():
   cmd.execute("SELECT DISTINCT category FROM  product_mgt")
   saa=cmd.fetchall()
   return render_template('Sel_StockAdd.html',val=saa)

@app.route('/index7',methods=['POST'])
def index7():
    data=request.form['de']
    print(data)
    cmd.execute("SELECT pid,`item_name` FROM `product_mgt` WHERE `category`='"+data+"'")
    a=cmd.fetchall()
    re=['0','select']
    for d in a:
        re.append(d[0])
        re.append(d[1])
    resp=make_response(jsonify(re))
    resp.status_code=200
    resp.headers['Access-Control-Allow-Origin'] = '*'
    return resp

@app.route('/Stk_Add',methods=['POST','GET'])
def Stk_Add():
    sid=session['sid']
    pid=request.form['item']
    Stock=request.form['textfield']
    cmd.execute("select * from stock_notification where pid='"+pid+"'")
    s=cmd.fetchone()
    if s is None:
        cmd.execute("insert into stock_notification values(null,'" + str(pid) + "','" + str( sid) + "','" + Stock + "',curdate())")
        con.commit()
    else:
        stk=int(s[3])+int(Stock)
        cmd.execute("update stock_notification set stock='"+str(stk)+"' where pid='"+pid+"'")
        con.commit()

    return '''<script>alert("inserted");window.location="/Sel_StockNoti"</script>'''

@app.route('/Update_stock',methods=['POST','GET'])
def Update_stock():
    id=request.args.get('id')
    session['id']=id
    cmd.execute("select stock_notification.stk_id,stock_notification.stock,product_mgt.category,product_mgt.item_name from stock_notification inner join product_mgt on stock_notification.pid=product_mgt.pid where stock_notification.stk_id='"+str(id)+"'")
    s=cmd.fetchone()
    print(s)
    return render_template('Sel_StockUpdate.html',val=s)

@app.route('/Upt_Stk',methods=['POST','GET'])
def Upt_Stk():
    id=session['id']
    Stock=request.form['textfield']
    cmd.execute("update stock_notification set stock='"+Stock+"' where stk_id='"+str(id)+"'")
    con.commit()
    return '''<script>alert("Updated Successfully");window.location='/Sel_StockNoti'</script>'''




if(__name__=='__main__'):
    app.run(debug=True)
