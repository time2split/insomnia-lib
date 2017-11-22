package insomnia.cpu;

import java.time.Duration;
import java.time.Instant;

public class CPUTimeBenchmark
{
	public Duration	sysTime			= Duration.ZERO;
	public Duration	usrTime			= Duration.ZERO;
	public Duration	cpuTime			= Duration.ZERO;
	public Duration	realTime		= Duration.ZERO;

	private long	start_sysTime	= -1;
	private long	start_usrTime	= -1;
	private long	start_cpuTime	= -1;
	private Instant	start_real		= null;

	public CPUTimeBenchmark()
	{
		// startChrono();
	}

	public CPUTimeBenchmark(CPUTimeBenchmark copy)
	{
		sysTime = copy.sysTime;
		usrTime = copy.usrTime;
		cpuTime = copy.cpuTime;
		realTime = copy.realTime;
		start_cpuTime = copy.start_cpuTime;
		start_real = copy.start_real;
		start_usrTime = copy.start_usrTime;
		start_sysTime = copy.start_sysTime;
	}

	public void startChrono()
	{
		start_real = Instant.now();
		start_cpuTime = CPUTime.getCpuTimeNano();
		start_usrTime = CPUTime.getUserTimeNano();
		start_sysTime = CPUTime.getSystemTimeNano();
	}

	public void stopChrono()
	{
		final long usr = CPUTime.getUserTimeNano();
		final long cpu = CPUTime.getCpuTimeNano();
		final long sys = CPUTime.getSystemTimeNano();
		usrTime = Duration.ofNanos(usr - start_usrTime);
		cpuTime = Duration.ofNanos(cpu - start_cpuTime);
		sysTime = Duration.ofNanos(sys - start_sysTime);
		realTime = Duration.between(start_real, Instant.now());
	}

	public void add(CPUTimeBenchmark time)
	{
		sysTime = sysTime.plus(time.sysTime);
		usrTime = usrTime.plus(time.usrTime);
		cpuTime = cpuTime.plus(time.cpuTime);
		realTime = realTime.plus(time.realTime);
	}

	@Override
	public String toString()
	{
		return "r" + realTime.toMillis() + " c" + cpuTime.toMillis() + " u"
				+ usrTime.toMillis() + " s" + sysTime.toMillis();
	}
	
	@Override
	public CPUTimeBenchmark clone()
	{
		return new CPUTimeBenchmark(this);
	}
}
