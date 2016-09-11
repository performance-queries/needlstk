def e2esum([e2e_latency, last_switch], [tout, tin, switch_id]):
  e2e_latency = e2e_latency + (tout - tin);
  last_switch = switch_id;
  emit();

def empty([], []):
  ;

R1 = SELECT e2esum FROM T GROUPBY [uid];
R3 = SELECT * FROM R1 WHERE e2e_latency > L and last_switch == egress_switch;
R4 = SELECT empty FROM R3 GROUPBY [srcip, dstip, srcport, dstport, proto];
