# -*- coding: utf-8 -*-
from pypdf import PdfReader, PdfWriter
import psycopg2
import datetime
import os
def completion(all_lines, field_name, field_data):
    for j in range(len(all_lines)):
        field_data["Text"+field_name[j]] = all_lines[j]
    return field_data

def len_splitting(data, data_len, field_name, field_data):
    words = data.split()
    line = ''
    all_lines = []
    j = 0
    if len(words) != 1:
        for i in range(len(words)):
            if len(line + ' ' + words[i]) <= data_len[j]:
                line += words[i]
                if i != len(words) - 1:
                    line += " "
            else:
                all_lines.append(line)
                line = words[i] + ' '
                j += 1
        if i == len(words) - 1:
             all_lines.append(line)
    else:
        all_lines.append(data)
    return completion(all_lines, field_name, field_data)
def change_field_data(DATA, FIELDS_NAME, FIELDS_LEN, field_data):
    for i in range(len(DATA)):
        try:
            if type(DATA[i]).__name__ == 'list':
                field_data = completion(DATA[i], FIELDS_NAME[i], field_data)
            else:
                if len(DATA[i]) >= 1:
                    field_data = len_splitting(DATA[i], FIELDS_LEN[i], FIELDS_NAME[i], field_data)
        except:
            print(DATA[i])
            print(FIELDS_LEN[i])
            print(FIELDS_NAME[i])

    return field_data

def R21001(DATA, FIELDS_LEN, FIELDS_NAME, field_data):
    filename = "E:\\LETI\\3_kurs\\IT-Projects\\startbusiness\\src\\main\\java\\ru\\studentproject\\startbusiness\\controllers\\R21001.pdf"
    pdf_reader = PdfReader(filename)
    pdf_writer = PdfWriter()
    pdf_writer.append(pdf_reader)
    field_data = change_field_data(DATA, FIELDS_NAME, FIELDS_LEN, field_data)
    for field, value in field_data.items():
        for page in pdf_writer.pages:
            try:
                pdf_writer.update_page_form_field_values(page, {field: value})
            except:
                pass
    new_file_name = "E:\\LETI\\3_kurs\\IT-Projects\\startbusiness\\R21001_" + DATA[6] + ".pdf"
    with open(new_file_name, 'wb') as output_file:
        pdf_writer.write(output_file)
def start_R21001():
    #__Start_DB__
    try:
        conn = psycopg2.connect(dbname='startbusiness', user='business_admin',
                                password='ct0mgB$eYQskZzFg74Qj#QMs', host='79.174.88.15', port='19975')
    except:
        print("ERROR CONNECT DB")
        return 0
    cursor = conn.cursor()

    cursor.execute('SELECT * FROM form WHERE status = 5')
    user_ids = []

    for row in cursor:
        user_ids.append(row)
    cursor.execute('SELECT * FROM employer WHERE form_id =' + str(user_ids[0][0]))
    for row in cursor:
        empl_infs = row
    cursor.execute('SELECT * FROM company WHERE form_id =' + str(user_ids[0][0]))
    for row in cursor:
        comp_infs = row
    cursor.execute('SELECT * FROM subject WHERE id =' + str(comp_infs[13]))
    for row in cursor:
        subj_infs = row


    cursor.execute('SELECT * FROM documents')
    for row in cursor:
        last_id = row[0]
    insert_query = """ INSERT INTO documents (id, date, file_path, form_id, name, user_id, type)
                                  VALUES (%s, %s, %s, %s, %s, %s, %s)"""
    item_purchase_time = datetime.datetime.now()
    file_path = os.getcwd()+"\\R21001_" + str(empl_infs[6]) + ".pdf"
    item_tuple = (int(last_id) + 1, item_purchase_time, file_path, user_ids[0][0], "R21001_" + str(empl_infs[6]) + ".pdf", 24, 2)
    cursor.execute(insert_query, item_tuple)
    print(item_tuple)
    conn.commit()
    cursor.close()
    conn.close()
    # __End_DB__

    ###___DATA___IP___
    FULL_NAME = empl_infs[14] + " " + empl_infs[11] + " " + empl_infs[10] # Format: "ВАСИЛЬЕВА АННА ВИКТОРОВНА"
    INN = str(empl_infs[6])  # Format: "783000229243"
    GENDER = str(empl_infs[18])  # Variants: "1"/"2" Format: "1"
    B_DATE = empl_infs[1].strftime('%d.%m.%Y')  # Format: "22.09.1993"
    B_CITY = empl_infs[2]  # Format: "г. САНКТ-ПЕТЕРБУРГ"
    CITIZENSHIP = str(empl_infs[3])  # Variants: "1"/"2"/"3" Format: "1"
    DOC_TYPE = str(empl_infs[4])  # Variants: "21"/????", DEFAULT = "21", Format: "21"
    DOC_NUM = str(empl_infs[16])[0:4] + " " + str(empl_infs[16])[4::] # Format: "0421 651128"
    DOC_DATE = empl_infs[8].strftime('%d.%m.%Y')  # Format: "06.12.2013"
    DOC_PLACE = str(empl_infs[9])  # Format: "УФМС РФ ПО г. САНКТ-ПЕТЕРБУРГ"
    DOC_CODE = str(empl_infs[7]).replace("-", "") # Format: "780005"
    ADRESS_S_RF = str(comp_infs[13]) # Format: "27"
    # ...
    ADRESS_1 = str(subj_infs[2]) # Format: "3"
    ADRESS_1_NAME = str(comp_infs[5]) # Format: "МО #65"
    ADRESS_2 = ""
    ADRESS_2_NAME = ""
    ADRESS_3 = ""
    ADRESS_3_NAME = ""
    ADRESS_4 = ""
    ADRESS_4_NAME = ""
    try:
        adr = str(comp_infs[8]).rfind(".")
    except:
        adr = str(comp_infs[8]).find(" ")
    ADRESS_5 = str(comp_infs[8])[0:adr+1] # Format: "ПР.-КТ"
    ADRESS_5_NAME = str(comp_infs[8])[adr+1::] # Format: "БОГАТЫРСКИЙ"
    # ...
    ADRESS_PLACE_1 = "д. " + str(comp_infs[2])  # Format: "д. 59|к. 1|к. 1"
    ADRESS_PLACE_2 = "офис " + str(comp_infs[6])   # Format: "кв. 21"
    ADRESS_PLACE_3 = ""  # Format: "кв. 59"
    EMAIL = empl_infs[15]  # Format: "ANNA.VS@MAIL.RU"
    PHONE = empl_infs[17].replace("(", "").replace(")", "").replace("-", "").replace(" ", "")  # Format: "+79117615542"
    MAIN_CODE = comp_infs[10].replace(".", " ")  # Format: "10 78 71"
    DOP_COD = comp_infs[1].replace(".", " ")  # Format: "10 78 72 47 78 24"
    ###___DATA___IP___

    DOP_CODS = ["", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""]
    for i in range(len(DOP_COD.split())):
        DOP_CODS[i] = DOP_COD.split()[i]
    ADRS = [["", ""], ["", ""], ["", ""]]
    if len(ADRESS_PLACE_1) != 0 and 1 == 0:
        for i in range(len(ADRESS_PLACE_1.split("|"))):
            ADRS[i] = ADRESS_PLACE_1.split("|")[i].split()
    else:
        ADRS = [[ADRESS_PLACE_1.split()[0], str(ADRESS_PLACE_1.split()[1])], ["", ""], ["", ""]]
    if len(ADRESS_PLACE_2) != 0:
        ADRESS_PLACE_2_1 = ADRESS_PLACE_2.split()[0]
        ADRESS_PLACE_2_2 = ADRESS_PLACE_2.split()[1]
    else:
        ADRESS_PLACE_2_1 = ""
        ADRESS_PLACE_2_2 = ""
    if len(ADRESS_PLACE_3) != 0:
        ADRESS_PLACE_3_1 = ADRESS_PLACE_3.split()[0]
        ADRESS_PLACE_3_2 = ADRESS_PLACE_3.split()[1]
    else:
        ADRESS_PLACE_3_1 = ""
        ADRESS_PLACE_3_2 = ""

    DATA_3 = [
        FULL_NAME.split()[0],
        FULL_NAME.split()[1],
        FULL_NAME.split()[2],
        "",
        "",
        "",
        INN,
        GENDER,
        [B_DATE.split(".")[0], B_DATE.split(".")[1], B_DATE.split(".")[2]],
        B_CITY,
        CITIZENSHIP,
        "",
        DOC_TYPE,
        DOC_NUM,
        [DOC_DATE.split(".")[0], DOC_DATE.split(".")[1], DOC_DATE.split(".")[2]],
        DOC_PLACE,
        [DOC_CODE[0:3], DOC_CODE[3::]],
        ADRESS_S_RF,
        ADRESS_1,
        ADRESS_1_NAME,
        ADRESS_2,
        ADRESS_2_NAME,
        ADRESS_3,
        ADRESS_3_NAME,
        ADRESS_4,
        ADRESS_4_NAME,
        ADRESS_5,
        ADRESS_5_NAME,
        ADRS[0][0],
        ADRS[0][1],
        ADRS[1][0],
        ADRS[1][1],
        ADRS[2][0],
        ADRS[2][1],
        ADRESS_PLACE_2_1,
        ADRESS_PLACE_2_2,
        ADRESS_PLACE_3_1,
        ADRESS_PLACE_3_2,
        "",
        "",
        "",
        "",
        "",
        "",
        EMAIL,
        "004",
        [MAIN_CODE.split()[0], MAIN_CODE.split()[1], MAIN_CODE.split()[2]],
        [DOP_CODS[0], DOP_CODS[1], DOP_CODS[2]], [DOP_CODS[3], DOP_CODS[4], DOP_CODS[5]],
        [DOP_CODS[6], DOP_CODS[7], DOP_CODS[8]],
        [DOP_CODS[9], DOP_CODS[10], DOP_CODS[11]], [DOP_CODS[12], DOP_CODS[13], DOP_CODS[14]],
        [DOP_CODS[15], DOP_CODS[16], DOP_CODS[17]],
        "005",
        EMAIL,
        "",
        PHONE,
        "",
        "",
        ""

    ]
    FIELDS_NAME_3 = [
        ["37.0.0"],
        ["37.0.1"],
        ["37.0.2"],
        ["37.0.3"],
        ["37.0.4"],
        ["37.0.5"],
        ["38"],
        ["61.1.1"],
        ["73.0", "73.1", "73.2"],
        ["74.0", "74.1"],
        ["61.1.0"],
        ["75"],
        ["122"],
        ["133"],
        ["115.0.1", "115.1.1", "115.2.1"],
        ["113.0.3.3", "113.0.3.4", "113.0.3.5"],
        ["136.0", "136.1"],
        ["85"],
        ["18"],
        ["33.0", "12.10"],
        ["21"],
        ["33.1", "12.11"],
        ["40.2.1"],
        ["33.2", "47.0"],
        ["51.0.0.0.1"],
        ["51.1.0.1.1.1", "51.0.1.0.1.1.0", "51.0.1.0.1.2.0"],
        ["51.0.0.0.2"],
        ["51.1.0.1.1.2", "51.0.1.0.1.1.1", "51.0.1.0.1.2.1"],
        ["52.0.0"],
        ["52.2.0"],
        ["52.0.1"],
        ["52.2.1"],
        ["52.0.2"],
        ["52.2.2"],
        ["52.0.3"],
        ["52.2.3"],
        ["52.0.4"],
        ["52.2.4"],
        ["91.1.0"],
        ["82.1.1"],
        ["72.0.1.0", "72.1.1.0", "72.2.1.0"],
        ["78.1.1.1.0", "78.1.1.1.2", "78.1.1.1.3"],
        ["91.1.1"],
        ["72.0.1.1", "72.1.1.1", "72.2.1.1"],
        ["78.1.1.1.1"],
        ["161"],
        ["162.0", "162.1", "162.2"],
        ["163.0.0", "163.0.1", "163.0.2"], ["163.0.3", "163.0.4", "163.0.5"], ["163.0.6", "163.0.7", "163.0.8"],
        ["163.0.9", "163.0.10", "163.0.11"], ["163.1.0", "163.1.1", "163.1.2"], ["163.1.3", "163.1.4", "163.1.5"],
        ["187"],
        ["200.0"],
        ["203.0"],
        ["189"],
        ["203.1.0"],
        ["203.1.1"],
        ["204"]
    ]
    FIELDS_LEN_3 = [
        [35],
        [35],
        [35],
        [35],
        [35],
        [35],
        [12],
        [1],
        [2, 2, 4],
        [40, 40],
        [1],
        [3],
        [2],
        [25],
        [2, 2, 4],
        [40, 40, 40],
        [3, 3],
        [2],
        [1],
        [19, 40],
        [1],
        [19, 40],
        [10],
        [19, 35],
        [10],
        [19, 40, 40],
        [10],
        [19, 40, 40],
        [10],
        [20],
        [10],
        [20],
        [10],
        [20],
        [10],
        [20],
        [10],
        [20],
        [1],
        [25],
        [2, 2, 4],
        [40, 40, 40],
        [1],
        [2, 2, 4],
        [40],
        [3],
        [2, 2, 2],
        [2, 2, 2], [2, 2, 2], [2, 2, 2], [2, 2, 2], [2, 2, 2], [2, 2, 2],
        [3],
        [40],
        [1],
        [20],
        [1],
        [12],

    ]
    R21001(DATA_3, FIELDS_LEN_3, FIELDS_NAME_3, {})
start_R21001()
